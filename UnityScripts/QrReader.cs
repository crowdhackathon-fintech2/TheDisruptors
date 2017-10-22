using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using ZXing;
using ZXing.QrCode;
using UnityEngine.UI;

public class QrReader : MonoBehaviour {
    public Transform itemDetails;

    private WebCamTexture camTexture;
    private Rect screenRect;
    private int time=60;

    void Start()
    {
        screenRect = new Rect(0, 0, Screen.width, Screen.height);
        
        camTexture = new WebCamTexture();
        
        camTexture.requestedHeight = Screen.height;
        camTexture.requestedWidth = Screen.width;
        //camTexture.videoRotationAngle=
        //transform.rotation =Quaternion.Euler(0, 0, 90.0f);
        //transform.LookAt()
        if (camTexture != null)
        {
            camTexture.Play();
        }
    }

    void OnGUI()
    {
        // drawing the camera on screen
        GUI.DrawTexture(screenRect, camTexture, ScaleMode.ScaleAndCrop);
        // do the reading — you might want to attempt to read less often than you draw on the screen for performance sake
        try
        {
            IBarcodeReader barcodeReader = new BarcodeReader();
            // decode the current frame
            var result = barcodeReader.Decode(camTexture.GetPixels32(), camTexture.width, camTexture.height);
            if (result != null)
            {
                Debug.Log("DECODED TEXT FROM QR: " +result.Text);
                //edw tha stelnw to qr string
                //get response with data 
                StartCoroutine("ItemFound");
            }
        }
        catch (Exception ex) { Debug.LogWarning(ex.Message); }
    }

    IEnumerable ItemFound()
    {
        itemDetails.gameObject.SetActive(true);
        yield return new WaitForSeconds(1.0f);
        for(int i = 1; i < time; i++)
        {
            itemDetails.Find("Timer").GetComponent<Text>().text = (time - i)+"";
            yield return new WaitForSeconds(1.0f);
        }
    
    }

   

}