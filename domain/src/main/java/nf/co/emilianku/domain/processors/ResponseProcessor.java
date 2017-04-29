package nf.co.emilianku.domain.processors;

import nf.co.emilianku.domain.model.DataContainer;

/**
 * Created by emilio on 28.04.17.
 */

public interface ResponseProcessor {

    boolean process(DataContainer container, String responseBody);
}
