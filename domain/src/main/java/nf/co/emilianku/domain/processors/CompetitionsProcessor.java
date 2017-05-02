package nf.co.emilianku.domain.processors;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nf.co.emilianku.domain.events.HttpResponseFailure;
import nf.co.emilianku.domain.events.HttpResponseOk;
import nf.co.emilianku.domain.model.Competition;
import nf.co.emilianku.domain.model.DataContainer;

/**
 * Created by emilio on 28.04.17.
 */

public class CompetitionsProcessor implements ResponseProcessor{

    @Override
    public boolean process(DataContainer container, String url, String responseBody) {
        container.clearCompetitions();
        if (responseBody != null) {
            try {
                JSONArray array = new JSONArray(responseBody);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject row = array.getJSONObject(i);
                    container.addCompetition(new Competition(
                            row.getJSONObject("_links").getJSONObject("teams").getString("href"),
                            row.getJSONObject("_links").getJSONObject("fixtures").getString("href"),
                            row.getJSONObject("_links").getJSONObject("leagueTable").getString("href"),
                            row.getInt("id"),
                            row.getString("caption"),
                            row.getInt("currentMatchday"),
                            row.getInt("numberOfMatchdays"),
                            row.getInt("numberOfTeams"),
                            row.getInt("numberOfGames")));
                }

                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
