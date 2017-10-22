using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PaymentMethod : MonoBehaviour {
    public List<GameObject> paymentIcons;
    public int activeIconNum;

    private void Start()
    {
        CloseAllIcons();
        activeIconNum=0;
        paymentIcons[activeIconNum].SetActive(true);
    }

    public void NextIcon()
    {
        CloseAllIcons();
        activeIconNum++;
        if (activeIconNum >= 3)
        {
            activeIconNum = 0;
        }
        paymentIcons[activeIconNum].SetActive(true);
    }

    public void PreviousIcon()
    {
        CloseAllIcons();
        activeIconNum--;
        if (activeIconNum < 0)
        {
            activeIconNum =paymentIcons.Count-1;
        }
        paymentIcons[activeIconNum].SetActive(true);
    }

    void CloseAllIcons()
    {
        foreach (GameObject temp in paymentIcons)
        {
            temp.SetActive(false);
        }
    }
}
