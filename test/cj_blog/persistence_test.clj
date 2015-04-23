(ns cj-blog.persistence-test
  (:use midje.sweet
        :require [cj-blog.persistence :as persistence]))

(def blog-to-add {:id "id" :title "title" :date "date"})
(facts "get all blogs"
  (against-background [(before :facts (clear-blogs))])
  (fact "no blogs returned intially"
    (get-all-blogs) => [])

  (fact "one blog returned intially"
    (against-background [(before :checks (add-blog blog-to-add))])
    (get-all-blogs) => [blog-to-add])
  )
