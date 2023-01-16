
package telegram.bot.finance.google.json;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class UsdToRub {

    @SerializedName("discover_more")
    private List<DiscoverMore> discoverMore;
    @Expose
    private List<Graph> graph;
    @SerializedName("knowledge_graph")
    private KnowledgeGraph knowledgeGraph;
    @Expose
    private Markets markets;
    @SerializedName("news_results")
    private List<NewsResult> newsResults;
    @SerializedName("search_metadata")
    private SearchMetadata searchMetadata;
    @SerializedName("search_parameters")
    private SearchParameters searchParameters;
    @Expose
    private Summary summary;

    public List<DiscoverMore> getDiscoverMore() {
        return discoverMore;
    }

    public void setDiscoverMore(List<DiscoverMore> discoverMore) {
        this.discoverMore = discoverMore;
    }

    public List<Graph> getGraph() {
        return graph;
    }

    public void setGraph(List<Graph> graph) {
        this.graph = graph;
    }

    public KnowledgeGraph getKnowledgeGraph() {
        return knowledgeGraph;
    }

    public void setKnowledgeGraph(KnowledgeGraph knowledgeGraph) {
        this.knowledgeGraph = knowledgeGraph;
    }

    public Markets getMarkets() {
        return markets;
    }

    public void setMarkets(Markets markets) {
        this.markets = markets;
    }

    public List<NewsResult> getNewsResults() {
        return newsResults;
    }

    public void setNewsResults(List<NewsResult> newsResults) {
        this.newsResults = newsResults;
    }

    public SearchMetadata getSearchMetadata() {
        return searchMetadata;
    }

    public void setSearchMetadata(SearchMetadata searchMetadata) {
        this.searchMetadata = searchMetadata;
    }

    public SearchParameters getSearchParameters() {
        return searchParameters;
    }

    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

}
