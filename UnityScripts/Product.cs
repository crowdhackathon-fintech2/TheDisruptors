using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Product{

    public string itemName;
    public float price;
    public string qr;
    public Sprite image;

    public Product(string _itemName,float _price,string _qr, Sprite _image)
    {
        itemName = _itemName;
        price = _price;
        qr = _qr;
        image = _image;
    }
}
