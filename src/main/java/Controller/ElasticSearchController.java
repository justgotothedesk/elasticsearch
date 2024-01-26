package Controller;

import Domain.Article;
import Repository.ElasticSearchQuery;
import co.elastic.clients.elasticsearch.nodes.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ElasticSearchController {
    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @PostMapping("/createOrUpdateDocument")
    public ResponseEntity<Object> createOrUpdateDocument(@RequestBody Article article) throws IOException {
        String reponse = elasticSearchQuery.createOrUpdateDocument(article);
        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    @GetMapping("/getDocument")
    public ResponseEntity<Object> getDocumentById(@RequestParam String id) throws IOException {
        Article article = elasticSearchQuery.getDocumentById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDocument")
    public ResponseEntity<Object> deleteDocumentById(@RequestParam String id) throws IOException {
        String reponse = elasticSearchQuery.deleteDocumentById(id);
        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    @GetMapping("/searchDocument")
    public ResponseEntity<Object> searchAllDocument() throws IOException {
        List<Article> articles = elasticSearchQuery.searchAllDocuments();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
}
