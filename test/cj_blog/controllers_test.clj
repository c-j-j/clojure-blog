(ns cj_blog.controllers-test
  (:use midje.sweet
   :require
        [cj-blog.controllers :as controllers]
        [cj-blog.persistence :as persistence]
        )
  )


(def all-blog-posts-response (vector {:id 1 :title "blog1" :date "10-10-2016"}))
(facts "home page controller"
  ;(fact "gets list of all blogs"
    ;(
     ;(:blogs (home-page))) => (contains [(contains {:title "Some Blog Title"})])
    ;(:blogs (home-page)) => (contains [(contains {:date "10-10-2015"})])
;)

  (fact "home page returns all blog posts"
    (with-redefs-fn {#'persistence/get-all-blogs (fn[]all-blog-posts-response )}
      #(:blogs (home-page))) => all-blog-posts-response)
  )
