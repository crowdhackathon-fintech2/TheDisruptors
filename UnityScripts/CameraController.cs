using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using ZXing;
using ZXing.QrCode;
using UnityEngine.UI;
using System;

public class CameraController : MonoBehaviour {
    public Transform itemDetails;
    public WebCamTexture mCamera = null;
    private WebCamDevice[] devices;
    public GameObject plane;
    private int time = 60;
    private bool itemfound = false;
    string resulttext;
    public Text pendingCart;
    public int pendingCartNum=0;
    List<Product> myProducts=new List<Product>();
    Product currentProduct;
    public Text itemName;
    public Text price;
    public Image itemImage;
    public Sprite mamaMia, Kaiser, LGTV, Chair,lamp,sofa,tvtable;


    public Text DebugText;
    // Use this for initialization
    void Start()
    {
        Populate();
        Debug.Log("Script has been started");
        plane = GameObject.FindWithTag("Player");
        devices = WebCamTexture.devices;
        mCamera = new WebCamTexture();
        mCamera.deviceName = devices[0].name;
        plane.GetComponent<Renderer>().material.mainTexture = mCamera;
        mCamera.Play();

        for (int i = 0; i < devices.Length; i++)
            DebugText.text += "\n" + devices[i].name;

    }

    void Populate()
    {
        myProducts.Add(new Product("Mama Mia musical",12.00f, "fastpayqr-gen-ASVDSDFP12", mamaMia));
        myProducts.Add(new Product("Kaiser Beer", 5.00f, "fastpayqr-gen-ASVDSDFP13", Kaiser));
        myProducts.Add(new Product("LG TV 72 4k", 1000.00f, "fastpayqr-gen-ASVDSDFP14", LGTV));
        myProducts.Add(new Product("Chair", 128.00f, "fastpayqr-gen-ASVDSDFP15", Chair));
        myProducts.Add(new Product("Lamp", 27.00f, "fastpayqr-gen-ASVDSDFP16", lamp));
        myProducts.Add(new Product("sofa", 335.00f, "fastpayqr-gen-ASVDSDFP17", sofa));
        myProducts.Add(new Product("TV Table", 50.00f, "fastpayqr-gen-ASVDSDFP18", tvtable));
    }

    // Update is called once per frame
    void Update()
    {
        try
        {
            IBarcodeReader barcodeReader = new BarcodeReader();
            // decode the current frame
            var result = barcodeReader.Decode(mCamera.GetPixels32(), mCamera.width, mCamera.height);
            if (result != null && !itemfound)
            {
                resulttext = result.Text;
                int i = 0;
                foreach (Product temp in myProducts)
                {
                    if (temp.qr == resulttext)
                    {
                        //store i
                        currentProduct = temp;
                        //edw tha stelnw to qr string
                        //get response with data 
                        StartCoroutine("ItemFound");
                    }
                    i++;
                }
                
            }
        }
        catch (Exception ex) { Debug.LogWarning(ex.Message); }
    }

    IEnumerator ItemFound()
    {
        itemName.text = currentProduct.itemName;
        price.text = currentProduct.price + ".00";
        itemImage.sprite = currentProduct.image;
        itemfound = true;
        Debug.Log("DECODED TEXT FROM QR: " + resulttext);
        itemDetails.gameObject.SetActive(true);
        yield return new WaitForSeconds(1.0f);
        for (int i = 1; i < time; i++)
        {
            itemDetails.Find("Background2").Find("Timer").GetComponent<Text>().text = "00:"+(time - i) + "";
            yield return new WaitForSeconds(1.0f);
        }
        NextScan();
    }


    private void OnDestroy()
    {
        mCamera.Stop();
    }

    public void NextScan()
    {
        itemfound = false;
        itemDetails.gameObject.SetActive(false);
    }

    public void AddToCart()
    {
        pendingCartNum++;
        pendingCart.text = pendingCartNum + "";

        NextScan();

    }

}
