(ns cj-blog.controllers
  (:require [cj-blog.persistence :as persistence]
            [clj-time.format :as f]
            [clj-time.local :as l]
            [clj-time.core :as t]
            ))

(def current-blog-id (atom 0))

(defn home-page "home page controller" []
  {:blogs (persistence/get-all-blogs)})

(defn add-id [params]
  (assoc params :id (str (swap! current-blog-id inc)))
  )

(defn add-date [params]
  (assoc params :date (l/format-local-time (l/local-now) :basic-date-time))
  )

(defn create-blog [params]
  (persistence/add-blog (add-date (add-id params))))

(defn blog-page [id]
  (persistence/get-blog id)
  )


