(ns cj-blog.handler-test
  (:use midje.sweet
        :require [clojure.test :refer :all]
        [ring.mock.request :as mock]
        [cj-blog.controllers :as controllers]
        [cj-blog.handler :refer :all]))

(def single-blog {:title "Some Blog Title" :content "Some Content" :date "Some Date"})

(def home-page-mock-response {:blogs [single-blog]})

(facts "home page tests"
  (fact "body contains blog titles"
    (app (mock/request :get "/")) => (contains {:body (contains "Some Blog Title")})
    (provided (controllers/home-page) => home-page-mock-response)))

(facts "new blog tests"
  (fact "posting new blog redirects back to home page"
    (app (mock/request :post "/new-blog" {:HELLO "World"})) => (contains {:status 302}) )
  (fact "posting new blog calls controller"
    (app (mock/request :post "/new-blog")) => anything
    (provided (controllers/create-blog anything) => nil)))

(facts "blog page"
  (fact "gets blog page from controller"
    (app (mock/request :get "/blog/some-id")) => (contains {:status 200})
    (app (mock/request :get "/blog/some-id")) => (contains {:body (contains "Some Content")})
    (provided (controllers/blog-page "some-id") => single-blog)))

