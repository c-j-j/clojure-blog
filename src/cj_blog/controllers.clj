(ns cj-blog.controllers
  (:require [cj-blog.persistence :as persistence]))

(def current-blog-id (atom 0))
(defn home-page "home page controller" []
  {:blogs (persistence/get-all-blogs)})

(defn create-blog [params]
  (persistence/add-blog (assoc params :id (str (swap! current-blog-id inc)))))

(defn blog-page [id]
  (persistence/get-blog id)
  )


