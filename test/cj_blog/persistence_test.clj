(ns cj-blog.persistence-test
  (:use midje.sweet
        :require [cj-blog.persistence :as persistence]))

(def blog-a {:id "id-a" :title "title" :date "date"})
(def blog-b {:id "id-b" :title "title" :date "date"})
(facts "get all blogs"
  (against-background [(before :facts (clear-blogs))])
  (fact "no blogs returned intially"
    (get-all-blogs) => [])

  (fact "one blog returned"
    (against-background [(before :checks (add-blog blog-a))])
    (get-all-blogs) => [blog-a])

  (fact "creates and retrieves blog"
    (against-background [(before :checks (do (add-blog blog-a)(add-blog blog-b)))])
    (get-blog "id-b") => blog-b)
  )

(facts "comments"
  (against-background [(before :facts (clear-blogs))])
  (fact "saves a single comment to a blog"
    (against-background [(before :checks (do (add-blog blog-a)(save-comment {:blog-id (:id blog-a) :comment "some-comment"})))])
    (get-blog (:id blog-a)) => (contains {:comment "some-comment"}))
  )
