package test.polak.shay.ladbrokes.data

import org.json.JSONObject

interface MovieDataListener
{
    fun onFailure()

    fun showServerData(data : JSONObject)

    fun showLocalData(data : Any)
}