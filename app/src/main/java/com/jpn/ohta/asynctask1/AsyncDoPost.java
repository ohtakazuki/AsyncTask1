package com.jpn.ohta.asynctask1;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * GETサンプル。型指定はそれぞれ以下に対応する
 * 第１：doInBackground
 * 第２：onProgressUpdate
 * 第３：doInBackgroundの戻り値、onPostExecuteの引数
 */
public class AsyncDoPost extends AsyncTask<String, Integer, Integer> {
    // コールバック用
    private CallBackTask callbacktask;

    // メッセージ格納用
    private String msg;

    private Context context;

    // コンストラクタ
    public AsyncDoPost(Context context) {
        super();
        this.context = context;
        msg = "";
    }

    // doInBackgroundの事前処理(UI操作可能)
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // 主処理(UI操作不可)
    @Override
    protected Integer doInBackground(String... params) {
        int ans = 0;

        HttpURLConnection connection = null;
        try {
            //参考：サーバサイドのコード
            //<?php
            //    $ret = 0;
            //    if(isset($_POST['str'])) {
            //        $str = $_POST['str'];
            //        eval(sprintf('$ret=%s;',$str));
            //    }
            //    $array = array("ans" => $ret,);
            //    header("Content-Type: text/javascript; charset=utf-8");
            //    echo json_encode($array);
            //?>

            // 接続先URL
            URL url = new URL("https://test2-ohtakazuki.c9users.io/GetPost/post1.php");

            connection = (HttpURLConnection) url.openConnection();
            // POSTの場合はwriteでパラメタを渡す
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write("str=" + URLEncoder.encode(params[0], "UTF-8"));
            out.close();

            final int status = connection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }

                // 取得した文字列からjsonobjectを作成
                JSONObject jsonObject = new JSONObject(sb.toString());
                ans = jsonObject.getInt("ans");
                msg = "正常に計算されました";
            } else {
                msg = "HTTP接続エラー";
            }

        } catch (MalformedURLException e) {
            msg = "MalformedURLException:" + e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            msg = "Exception:" + e.getMessage();
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return ans;
    }

    // 進捗状況をUIに反映するための処理(UI操作可能)
    @Override
    protected void onProgressUpdate(Integer... progress) {
    }

    // doInBackgroundの事後処理(UI操作可能)
    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        callbacktask.CallBack(result);
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    // 呼び出し元で、CallBackTaskを割り当てる
    public void setOnCallBack(CallBackTask _cbj) {
        callbacktask = _cbj;
    }

    // コールバック用のstaticなclass
    public static class CallBackTask {
        // 呼び出し側でoverrideする
        public void CallBack(int result) {
        }
    }
}
