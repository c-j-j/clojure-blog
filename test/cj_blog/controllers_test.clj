(ns cj_blog.controllers-test
  (:use midje.sweet
        :require
        [ring.mock.request :as mock]
        [cj-blog.controllers :as controllers]
        [cj-blog.persistence :as persistence])
  )

(def single-blog {:id 1 :title "blog1" :date "10-10-2016"})
(def all-blog-posts-response (vector single-blog))
(facts "home page controller"
  (fact "home page returns all blog posts"
    (:blogs (home-page)) => all-blog-posts-response
    (provided
      (persistence/get-all-blogs) => all-blog-posts-response)
    )
  )

(def form-data {:title "title" :date "date" :content "content"})
(facts "create blog"
  (fact "saves now blog by calling persistence api"
    (create-blog form-data) => nil
    (provided
      (persistence/add-blog (contains form-data)) => nil))

  (fact "saves blog with new id"
    (against-background [(before :checks (reset! controllers/current-blog-id 0))])
    (create-blog form-data) => nil
    (provided
      (persistence/add-blog (contains {:id "1"})) => nil))
  )

(facts "blog page"
  (fact "gets blog from persistence"
    (blog-page "some-id") => single-blog
    (provided
      (persistence/get-blog "some-id") => single-blog)))
