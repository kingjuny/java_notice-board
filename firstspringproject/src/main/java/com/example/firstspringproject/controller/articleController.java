package com.example.firstspringproject.controller;

import com.example.firstspringproject.dto.articleForm;
import com.example.firstspringproject.entitiy.Article;
import com.example.firstspringproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j// 로깅을 위한 골뱅이(어노테이션)
public class articleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(articleForm form){
        log.info(form.toString());
        //    System.out.println(form.toString());  log로변환

            //1. dto를 변환 entity
        Article article = form.toEntity();
        log.info(article.toString());
      //  System.out.println(article.toString());

        //2.repository에게 entity를 DB에 저장하게함
        Article saved= articleRepository.save(article);
        log.info(saved.toString());
        // System.out.println(saved.toString());

        return "redirect:/articles/"+saved.getId();
    }
    @GetMapping("/articles/{id}")
        public String show(@PathVariable Long id, Model model){
        log.info("id="+id);
        // 1. id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("article",articleEntity);
        // 3. 보여줄 페이지를 설정
            return "articles/show";
    }

    @GetMapping("/articles")
        public String index(Model model){
        List<Article> articleEntityList=articleRepository.findAll();
        model.addAttribute("articleList",articleEntityList);
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
        public String edit(@PathVariable Long id,Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",articleEntity);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    String update(articleForm form){
        log.info(form.toString());

        //1. DTO를 엔티티로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        //2. 엔티티를 DB로 저장
        //2-1 DB에서 기존 데이터를 가져온다
        Article target= articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2 기존데이터가 있다면 값을 갱신!
        if (target !=null){
            articleRepository.save(articleEntity);
        }
        log.info(target.toString());
        //3. 수정결과 페이지를 리다이렉트
        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    String delete(@PathVariable Long id, RedirectAttributes rttr){
        Article target =articleRepository.findById(id).orElse(null);
        log.info("삭제요청이 들어왔습니다.");
        if (target!=null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다.");
        }

        return "redirect:/articles";
    }

}
