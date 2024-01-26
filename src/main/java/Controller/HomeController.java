package Controller;

import Domain.Article;
import Repository.ElasticSearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class HomeController {
    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @GetMapping("/")
    public String viewHomePage(Model model) throws IOException {
        model.addAttribute("listArticleDocuments",elasticSearchQuery.searchAllDocuments());
        return "home";
    }

    @PostMapping("/saveProduct")
    public String saveArticle(@ModelAttribute("article") Article article) throws IOException {
        elasticSearchQuery.createOrUpdateDocument(article);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") String id, Model model) throws IOException {

        Article article = elasticSearchQuery.getDocumentById(id);
        model.addAttribute("article", article);
        return "updateArticleDocument";
    }

    @GetMapping("/showNewArticleForm")
    public String showNewEmployeeForm(Model model) {
        Article article = new Article();
        model.addAttribute("article", article);
        return "newArticleDocument";
    }

    @GetMapping("/deleteArticle/{id}")
    public String deleteArticle(@PathVariable(value = "id") String id) throws IOException {

        this.elasticSearchQuery.deleteDocumentById(id);
        return "redirect:/";
    }

}