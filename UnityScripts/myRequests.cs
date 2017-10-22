using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class myRequests : MonoBehaviour {

    public string productUrl = "http://10.35.250.2:8080/product/getDetails";
    public string nbgPayURl = "http://10.35.250.2:8080/product/makeNBGPayment";//id 1
    public string bitCoinPayURl = "http://10.35.250.2:8080/product/payMC";//id 1
    public string masterCardPayURl = "http://10.35.250.2:8080/product/payBC";//id 1

    public string screenShotURL = "http://www.my-server.com/cgi-bin/screenshot.pl";

    private void Start()
    {
        fetchNewProduct("1");
    }

    public void fetchNewProduct(string productHash)
    {
        StartCoroutine(FetchIt(productHash));
    }

    public IEnumerator FetchIt(string productHash)
    {
        // Create a form object for sending high score data to the server
        WWWForm form = new WWWForm();
        // Assuming the perl script manages high scores for different games
        form.AddField("id", productHash);


        // Create a download object
        WWW download = new WWW(productUrl, form);

        // Wait until the download is done
        yield return download;

        if (!string.IsNullOrEmpty(download.error))
        {
            print("Error downloading: " + download.error);
        }
        else
        {
            // show the highscores
            Debug.Log(download.text);
            TheProduct(download.text);
        }
    }

    public myProduct TheProduct( string jsonString)
    {//write ite elsewhere or something.... 
        return JsonUtility.FromJson<myProduct>(jsonString);
    }

    public IEnumerator UploadPNG()
    {
        // We should only read the screen after all rendering is complete
        yield return new WaitForEndOfFrame();

        // Create a texture the size of the screen, RGB24 format
        int width = Screen.width;
        int height = Screen.height;
        var tex = new Texture2D(width, height, TextureFormat.RGB24, false);

        // Read screen contents into the texture
        tex.ReadPixels(new Rect(0, 0, width, height), 0, 0);
        tex.Apply();

        // Encode texture into PNG
        byte[] bytes = tex.EncodeToPNG();
        Destroy(tex);

        // Create a Web Form
        WWWForm form = new WWWForm();
        form.AddField("frameCount", Time.frameCount.ToString());
        form.AddBinaryData("fileUpload", bytes, "screenShot.png", "image/png");

        // Upload to a cgi script
        WWW w = new WWW(screenShotURL, form);
        yield return w;
        if (!string.IsNullOrEmpty(w.error))
        {
            print(w.error);
        }
        else
        {
            print("Finished Uploading Screenshot");
            Debug.Log(w.text);
        }
    }
}
