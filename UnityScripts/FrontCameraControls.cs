using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using ZXing;
using ZXing.QrCode;
using UnityEngine.UI;
using System;

public class FrontCameraControls : MonoBehaviour {
    public WebCamTexture mCamera = null;
    private WebCamDevice[] devices;
    public GameObject plane;
    // Use this for initialization
    void Start () {
        Debug.Log("Script has been started");
        plane = GameObject.FindWithTag("Player");
        devices = WebCamTexture.devices;
        mCamera = new WebCamTexture();
        mCamera.deviceName = devices[1].name;
        plane.GetComponent<Renderer>().material.mainTexture = mCamera;
        mCamera.Play();
    }
	
	// Update is called once per frame
	void Update () {
		
	}
    private void OnDestroy()
    {
        mCamera.Stop();
    }
}
