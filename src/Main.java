import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;

public class Main {
            // Replace <Subscription Key> with your valid subscription key.
            private static final String subscriptionKey = "f99a86bd80cd440c8b75d84743b90df2";

            private static final String uriBase =
                    "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect";

            private static final String imageWithFaces =
                    "{\"url\":\"https://upload.wikimedia.org/wikipedia/commons/c/c3/RH_Louise_Lillian_Gish.jpg\"}";

            private static final String faceAttributes =
                    "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise";

        public static void main(String[] args) throws IOException {
            String faceId1 = FaceRecognize();
            FaceCompare(faceId1, "filler");

        }

        public static String FaceRecognize(){
            HttpClient httpclient = HttpClientBuilder.create().build();
            try {
                URIBuilder builder = new URIBuilder(uriBase);
                // Request parameters. All of them are optional.
                builder.setParameter("returnFaceId", "true");
                builder.setParameter("returnFaceLandmarks", "false");
                builder.setParameter("returnFaceAttributes", faceAttributes);

                // Prepare the URI for the REST API call.
                URI uri = builder.build();
                HttpPost request = new HttpPost(uri);

                // Request headers.
                request.setHeader("Content-Type", "application/json");
                request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

                // Request body.
                StringEntity reqEntity = new StringEntity(imageWithFaces);
                request.setEntity(reqEntity);

                // Execute the REST API call and get the response entity.
                HttpResponse response = httpclient.execute(request);
                HttpEntity entity = response.getEntity();

                if (entity != null)
                {
                    // Format and display the JSON response.
                    System.out.println("REST Response:\n");

                    String jsonString = EntityUtils.toString(entity).trim();
                    if (jsonString.charAt(0) == '[') {
                        JSONArray jsonArray = new JSONArray(jsonString);
                        System.out.println(jsonArray.toString(2));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (jsonObject.has("faceId")) {
                                System.out.println("works 1");
                                return jsonObject.getString("faceId");
                            }
                        }
                    }
                    else if (jsonString.charAt(0) == '{') {
                        JSONObject jsonObject1 = new JSONObject(jsonString);
                        System.out.println(jsonObject1.toString(2));
                        String code = jsonObject1.getString("faceId");
                        System.out.println("works2");
                        return code;
                    } else {
                        System.out.println(jsonString);
                        return null;
                    }
                }
            }
            catch (Exception e)
            {
                // Display error message.
                System.out.println(e.getMessage());
            }
            return null;
        }

        private static void FaceCompare(String faceid1, String faceid2){
            String inputJson =  "{\"faceId1\": \"" + faceid1 + "\"," +
                    "\"faceId2\": \"" + "5e21d577-b7a4-4376-9173-05bc0bd6ac38" + "\"}";

            HttpClient httpclient = HttpClients.createDefault();
            try {

                URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/verify");
                URI uri = builder.build();
                HttpPost request = new HttpPost(uri);
                request.setHeader("Content-Type", "application/json");
                request.setHeader("Ocp-Apim-Subscription-Key", "f99a86bd80cd440c8b75d84743b90df2");

                // Request body
                StringEntity reqEntity = new StringEntity(inputJson);
                request.setEntity(reqEntity);

                HttpResponse response = httpclient.execute(request);
                HttpEntity entity = response.getEntity();
                if (entity != null)
                {
                    System.out.println(EntityUtils.toString(entity));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}



