package services;	
import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.thebookdb.Bar;
import model.thebookdb.Baz;
import model.thebookdb.BookResult;
import model.thebookdb.ErrorResponse;
import model.thebookdb.Foo;
import model.thebookdb.Properties;
import exception.BookAPIException;


public class BookAPIService {
		private final String API_URL;
		private final String API_KEY;
		
		public BookAPIService(String API_URL,String API_KEY) {
			this.API_URL = API_URL;
			this.API_KEY = API_KEY;
		}
		private BookResult getAPIData(String apiFunction,String parameter,String API_URL,String API_KEY)
				throws BookAPIException{
			try {
				
				final URIBuilder uriBuilder = new URIBuilder(API_URL)
					.setPathSegments("3", apiFunction, "movie")
					.addParameter("api_key", API_KEY);
			if (parameter != null && !parameter.isBlank()) {
				switch (apiFunction) {
				case "search":
					uriBuilder.addParameter("query", parameter);
					break;
				case "discover":
					uriBuilder.addParameter("primary_release_year", parameter);
					break;
				}
			}
			final URI uri = uriBuilder.build();
			
			final HttpGet getRequest = new HttpGet(uri);
			final CloseableHttpClient httpclient = HttpClients.createDefault();
			try (CloseableHttpResponse response = httpclient.execute(getRequest)) {
				final HttpEntity entity = response.getEntity();
				final ObjectMapper mapper = new ObjectMapper();

				if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
					ErrorResponse errorResponse = mapper.readValue(entity.getContent(), ErrorResponse.class);
					if (errorResponse.getStatusMessage() != null)
						throw new BookAPIException("Error occurred on API call: " + errorResponse.getStatusMessage());
				}

				return mapper.readValue(entity.getContent(), BookResult.class);
			} catch (IOException e) {
				throw new BookAPIException("Error requesting data from the TheΒοοκDB API.", e);
			}
		} catch (URISyntaxException e) {
			throw new BookAPIException("Unable to create request URI.", e);
		}
		
}
}

