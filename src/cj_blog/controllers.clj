(ns cj-blog.controllers
  (:require [cj-blog.persistence :as persistence]
            [clj-time.local :as l]
            [clj-time.format :as f]
            [clj-time.core :as t]
            ))

(def current-blog-id (atom 0))

(def date-formatter (f/formatters :mysql))

(defn- format-date [blog]
  (update-in blog [:date] (partial f/unparse date-formatter)))

(defn- format-dates [blogs]
 (map #(format-date %) blogs))

(defn home-page "home page controller" []
  {:blogs (format-dates (persistence/get-all-blogs))})

(defn add-id [params]
  (assoc params :id (str (swap! current-blog-id inc))))

(defn add-date [params]
  (assoc params :date (l/local-now)))

(defn create-blog [params]
  (persistence/add-blog (add-date (add-id params))))

(defn blog-page [id]
  (format-date (persistence/get-blog id)))
