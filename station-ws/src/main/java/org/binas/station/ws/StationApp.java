package org.binas.station.ws;

import org.binas.station.domain.Station;

/**
 * The application is where the service starts running. The program arguments
 * are processed here. Other configurations can also be done here.
 */
public class StationApp {

	public static void main(String[] args) throws Exception {
		// Check arguments
		if (args.length < 3) {
			System.err.println("Argument(s) missing!");
			System.err.println("Usage: java " + StationApp.class.getName() + "wsName uddiURL");
			return;
		}

		String wsName = args[0];	//wsname
		String wsURL = args[1];
		String uddiURL = args[2];	//endereco uddi

		StationEndpointManager endpoint = new StationEndpointManager(uddiURL, wsName, wsURL);
		Station.getInstance().setId(wsName);

		System.out.println(StationApp.class.getSimpleName() + " running");

		try {

			endpoint.start();

			endpoint.awaitConnections();

			endpoint.stop();

		} catch (Exception e) {
			System.out.printf("Caught exception: %s%n", e);
			e.printStackTrace();

	}	}

}