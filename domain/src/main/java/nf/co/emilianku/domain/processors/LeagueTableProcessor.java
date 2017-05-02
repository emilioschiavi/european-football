package nf.co.emilianku.domain.processors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nf.co.emilianku.domain.model.DataContainer;
import nf.co.emilianku.domain.model.LeagueTableEntry;

/**
 * Created by emilio on 02.05.17.
 */

public class LeagueTableProcessor implements ResponseProcessor{

    @Override
    public boolean process(DataContainer container, String url, String responseBody) {
        if (responseBody != null) {
            try {
                JSONObject obj = new JSONObject(responseBody);
                JSONArray array = obj.getJSONArray("standing");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject row = array.getJSONObject(i);
                    container.addLeagueTableEntry(url, new LeagueTableEntry(
                            row.getString("teamName"),
                            row.getInt("points")));
                }

                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
