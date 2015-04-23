(ns cj-blog.handler-test
  (:use midje.sweet
        :require [clojure.test :refer :all]
        [ring.mock.request :as mock]
        [cj-blog.controllers :as controllers]
        [cj-blog.handler :refer :all]))

(def home-page-mock-response {:blogs [{:title "Some Blog Title" :date "Some Date"}]})

(facts "main route"
  (fact "body"
    (with-redefs-fn {#'controllers/home-page (fn[] home-page-mock-response)}
      #(app (mock/request :get "/"))) => (contains {:body (contains "Some Blog Title")}))

  (fact "status"
    (app (mock/request :get "/")) => (contains {:status 200}))
  )

