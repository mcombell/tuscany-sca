package services.json.rpc;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONRpc {

	protected JSONRpc() {

	}

	public static JSONObject invoke(String serviceURI, String rpcRequest) throws JSONException{
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(serviceURI);

		JSONObject result = null;
		try {
			httpPost.setHeader("Content-Type", "text/xml");
			httpPost.setEntity(new StringEntity(rpcRequest));

			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String jsonResult = EntityUtils.toString(httpResponse.getEntity());
				result = new JSONObject(jsonResult);
			} else {
				String errorMessage = httpResponse.getStatusLine()
						.getReasonPhrase();
				System.out.println(errorMessage);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
