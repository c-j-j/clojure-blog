(ns cj-blog.controllers
  (:require [cj-blog.persistence :as persistence]))

(defn home-page "home page controller" []
  {:blogs (persistence/get-all-blogs)}
  )
