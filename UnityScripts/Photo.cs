using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Photo : MonoBehaviour {
    public Text countdownText;


    public void TakePhoto()
    {
        countdownText.text = "3";
        countdownText.gameObject.SetActive(true);
        StartCoroutine(CountDown());
    }

    IEnumerator CountDown()
    {
        yield return new WaitForSeconds(1.0f);
        countdownText.text = "2";
        yield return new WaitForSeconds(1.0f);
        countdownText.text = "1";
        yield return new WaitForSeconds(1.0f);
        countdownText.gameObject.SetActive(false);
        yield return new WaitForSeconds(1.0f);
        countdownText.text = "Authorized";
        countdownText.gameObject.SetActive(true);
        yield return new WaitForSeconds(0.5f);
        countdownText.gameObject.SetActive(false);
        this.GetComponent<SceneBehavoiur>().ChangeScene("OK");
    }

}
