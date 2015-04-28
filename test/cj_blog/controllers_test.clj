(ns cj_blog.controllers-test
  (:use midje.sweet
        :require
        [ring.mock.request :as mock]
        [cj-blog.controllers :as controllers]
        [clj-time.local :as l]
        [clj-time.format :as f]
        [cj-blog.persistence :as persistence]))

(def current-time (l/local-now))
(def current-time-formatted (f/unparse controllers/date-formatter current-time))

(def single-blog {:id 1 :title "blog1" :date current-time})
(def form-data {:title "title" :content "content"})
(def all-blog-posts-response (vector single-blog))

(facts "home page controller"
  (fact "home page returns all blog posts"
    (:blogs (home-page)) => (contains [(contains {:id 1 :title "blog1"})])
    (provided
      (persistence/get-all-blogs) => all-blog-posts-response))

  (fact "all blog posts have their dates formatted"
    (:blogs (home-page)) => (contains [(contains {:date current-time-formatted})])
    (provided
      (persistence/get-all-blogs) => all-blog-posts-response)) )

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

  (fact "saves blog with current local date"
    (create-blog form-data) => nil
    (provided
      (l/local-now) => "SomeDate"
      (persistence/add-blog (contains {:date "SomeDate"})) => nil)))

(facts "blog page"
  (fact "gets blog from persistence"
    (blog-page "some-id") => (contains {:id 1 :title "blog1"})
    (provided
      (persistence/get-blog "some-id") => single-blog))

  (fact "blog has date formatted"
    (blog-page "some-id") => (contains {:date (f/unparse controllers/date-formatter current-time)})
    (provided
      (persistence/get-blog "some-id") => single-blog)))
