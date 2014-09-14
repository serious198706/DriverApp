package com.cy.library.service;

import android.util.Log;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by 岩 on 13-12-28.
 *
 * SoapService
 */
public class SoapService {
    private String resultMessage;

    private String url;
    private String soapAction;
    private String methodName;

    public SoapService() {}

    /**
     * 设置url, methodName
     * @param url
     * @param methodName
     */
    public void setParams(String url, String methodName) {
        this.url = url;
        this.soapAction = Constants.SOAP_ACTION;
        this.methodName = methodName;
    }

    // 获取结果信息
    public void setResultMessage(String resultMessage) { this.resultMessage = resultMessage; }
    public String getResultMessage() { return resultMessage; }

    /**
     * 通讯，如登录、提交信息等
     * @param methodName 要调用的方法名称
     * @param jsonContent 传递的数据
     * @return 是否成功
     */
    public String communicateWithServer(String methodName, String jsonContent) {
        resultMessage = "";

        SoapObject request = new SoapObject(Constants.NAMESPACE, this.methodName);
        request.addProperty("MethodName", methodName);
        request.addProperty("JsonContent", jsonContent);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); // ??
        envelope.bodyOut = request;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE trans = new HttpTransportSE(this.url);

        try {
            trans.call(this.soapAction, envelope);
        } catch (Exception e) {
            if(e.getMessage() != null)
                Log.d(Constants.CONNECTION_ERROR, "无法连接到服务器：" + e.getMessage());
            else
                Log.d(Constants.CONNECTION_ERROR, "无法连接到服务器！");

            resultMessage = "无法连接到服务器！";
            e.printStackTrace();

            return resultMessage;
        }

        // 收到的结果
        SoapObject soapObject = (SoapObject) envelope.bodyIn;

        resultMessage = soapObject.getProperty(0).toString();

        return resultMessage;
    }
}