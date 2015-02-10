package edu.udo.cs.ls14.jf.nlp;


/**
 * Ignoring, because of temporarily unavailability
 * 
 * @author JulianFlake
 *
 */
public class Wortschatz {
/*
	private static final Logger LOG = LoggerFactory.getLogger(Wortschatz.class);

	private static final String USERNAME = "anonymous";
	private static final String PASSWORD = "anonymous";
	private static final String SYNONYMS_URL = "http://wortschatz.uni-leipzig.de/axis/services/Synonyms?wsdl";
	private static final String BASEFORM_URL = "http://wortschatz.uni-leipzig.de/axis/services/Baseform?wsdl";
	private static final String THESAURUS_URL = "http://wortschatz.uni-leipzig.de/axis/services/Thesaurus?wsdl";
	private static final String SIMILARITY_URL = "http://wortschatz.uni-leipzig.de/axis/services/Similarity?wsdl";

	public static List<String> synonyms(String corpus, String word, int limit)
			throws Exception {
		URL url = new URL(SYNONYMS_URL);
		Synonyms synonyms = new synonyms.SynonymsService(url).getSynonyms();
		if (!synonyms.ping().equals("Webservice \"Synonyms\" is ready.")) {
			throw new Exception("Service currently not available ("
					+ url.toString() + ")");
		}

		((BindingProvider) synonyms).getRequestContext().put(
				BindingProvider.USERNAME_PROPERTY, USERNAME);
		((BindingProvider) synonyms).getRequestContext().put(
				BindingProvider.PASSWORD_PROPERTY, PASSWORD);

		synonyms.DataMatrix matrix = new synonyms.DataMatrix();
		matrix.getDataVectors().add(getVector("Wort", word));
		matrix.getDataVectors()
				.add(getVector("Limit", Integer.toString(limit)));

		synonyms.RequestParameter params = new synonyms.RequestParameter();
		params.setCorpus(corpus);
		params.setParameters(matrix);

		synonyms.ResponseParameter response = synonyms.execute(params);
		// TODO: collect results
		LOG.debug("Synonyms of " + word + ":");
		response.getResult().getDataVectors().stream().map(v -> v.getDataRow())
				.forEach(r -> LOG.debug(" " + r));
		return response.getResult().getDataVectors().stream()
				.map(v -> v.getDataRow().get(0)).collect(Collectors.toList());
	}

	public static String baseform(String corpus, String word) throws Exception {
		URL url = new URL(BASEFORM_URL);
		Baseform baseform = new BaseformService(url).getBaseform();
		if (!baseform.ping().equals("Webservice \"Baseform\" is ready.")) {
			throw new Exception("Service currently not available ("
					+ url.toString() + ")");
		}

		((BindingProvider) baseform).getRequestContext().put(
				BindingProvider.USERNAME_PROPERTY, USERNAME);
		((BindingProvider) baseform).getRequestContext().put(
				BindingProvider.PASSWORD_PROPERTY, PASSWORD);

		baseform.DataMatrix matrix = new baseform.DataMatrix();
		matrix.getDataVectors().add(getVector("Wort", word));

		baseform.RequestParameter params = new baseform.RequestParameter();
		params.setCorpus(corpus);
		params.setParameters(matrix);

		baseform.ResponseParameter response = baseform.execute(params);
		// TODO: collect results
		LOG.debug("Baseform of '" + word + "': ");
		response.getResult().getDataVectors().stream().map(v -> v.getDataRow())
				.forEach(v -> LOG.debug(" " + v));
		List<String> baseforms = response.getResult().getDataVectors().stream()
				.map(v -> v.getDataRow()).filter(r -> !r.get(1).equals("S"))
				.map(r -> r.get(0)).collect(Collectors.toList());
		return baseforms.isEmpty() ? null : baseforms.get(0);
	}

	public static List<String> thesaurus(String corpus, String word, int limit)
			throws Exception {
		URL url = new URL(THESAURUS_URL);
		Thesaurus thesaurus = new ThesaurusService(url).getThesaurus();
		if (!thesaurus.ping().equals("Webservice \"Thesaurus\" is ready.")) {
			throw new Exception("Service currently not available ("
					+ url.toString() + ")");
		}

		((BindingProvider) thesaurus).getRequestContext().put(
				BindingProvider.USERNAME_PROPERTY, USERNAME);
		((BindingProvider) thesaurus).getRequestContext().put(
				BindingProvider.PASSWORD_PROPERTY, PASSWORD);

		thesaurus.DataMatrix matrix = new thesaurus.DataMatrix();
		matrix.getDataVectors().add(getVector("Wort", word));
		matrix.getDataVectors()
				.add(getVector("Limit", Integer.toString(limit)));

		thesaurus.RequestParameter params = new thesaurus.RequestParameter();
		params.setCorpus(corpus);
		params.setParameters(matrix);

		thesaurus.ResponseParameter response = thesaurus.execute(params);
		// TODO: collect results
		LOG.debug("Thesaurus of " + word + ":");
		response.getResult().getDataVectors().stream()
				.forEach(v -> LOG.debug("- " + v.getDataRow()));
		return null;
	}

	public static List<String> similarity(String corpus, String word, int limit)
			throws Exception {
		URL url = new URL(SIMILARITY_URL);
		SimilarityService service = new SimilarityService(url);
		Similarity similarity = service.getSimilarity();
		if (!similarity.ping().equals("Webservice \"Similarity\" is ready.")) {
			throw new Exception("Service currently not available ("
					+ url.toString() + ")");
		}

		((BindingProvider) similarity).getRequestContext().put(
				BindingProvider.USERNAME_PROPERTY, USERNAME);
		((BindingProvider) similarity).getRequestContext().put(
				BindingProvider.PASSWORD_PROPERTY, PASSWORD);

		similarity.DataMatrix matrix = new similarity.DataMatrix();
		matrix.getDataVectors().add(getVector("Wort", word));
		matrix.getDataVectors()
				.add(getVector("Limit", Integer.toString(limit)));

		similarity.RequestParameter params = new similarity.RequestParameter();
		params.setCorpus(corpus);
		params.setParameters(matrix);

		similarity.ResponseParameter response = similarity.execute(params);
		// TODO: collect results
		LOG.debug("Similarity of " + word + ":");
		response.getResult()
				.getDataVectors()
				.stream()
				.forEach(
						v -> LOG.debug("- [" + v.getDataRow().get(2) + "] "
								+ v.getDataRow().get(1)));
		return null;
	}

	private static DataVector getVector(String key, String value) {
		DataVector v = new DataVector();
		v.getDataRow().add(key);
		v.getDataRow().add(value);
		return v;
	}
*/
}
