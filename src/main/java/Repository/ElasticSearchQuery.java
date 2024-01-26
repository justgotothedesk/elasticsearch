package Repository;

import Domain.Article;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ElasticSearchQuery {
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private final String indexName = "articles";

    public String createOrUpdateDocument(Article article) throws IOException {
        IndexResponse response = elasticsearchClient.index(i ->
                i.index(indexName)
                        .id(article.getId())
                        .document(article));
        if(response.result().name().equals("Created")) {
            return new StringBuilder("Article has been successfully created.").toString();
        } else if(response.result().name().equals("Updated")) {
            return new StringBuilder("Article has been successfully updated.").toString();
        }
        return new StringBuilder("Error").toString();
    }

    public Article getDocumentById(String id) throws IOException {
        Article article = null;
        GetResponse<Article> response = elasticsearchClient.get(g -> g
                .index(indexName)
                .id(id),
                Article.class);
        if (response.found()) {
            article = response.source();
            System.out.println("Article "+article.getTitle());
        } else {
            System.out.println("Article not found");
        }
        return article;
    }

    public String deleteDocumentById(String id) throws IOException {
        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(id));
        DeleteResponse response = elasticsearchClient.delete(request);

        if (Objects.nonNull(response.result()) && !response.result().name().equals("NotFound")) {
            return new StringBuilder("Article with id " + response.id() + " has been deleted.").toString();
        }
        System.out.println("Article not found");
        return new StringBuilder("Article with id " + response.id()+" does not exist.").toString();
    }

    public List<Article> searchAllDocuments() throws IOException {
        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, Article.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<Article> articles = new ArrayList<>();
        for(Hit object : hits) {
            System.out.print(((Article) object.source()));
            articles.add((Article)object.source());
        }
        return articles;
    }
}
