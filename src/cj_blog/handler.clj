(ns cj-blog.handler
  (
   :require [compojure.core :refer :all]
   [compojure.route :as route]
   [ring.util.response :refer :all]
   [compojure.handler :refer [site]]
   [ring.middleware.anti-forgery :refer :all]
   [ring.util.anti-forgery :refer :all]
   [cj-blog.controllers :as controllers]
   [ring.middleware.session :refer [wrap-session]]
   [ring.middleware.session.cookie :refer (cookie-store)]
   [selmer.parser :refer [render render-file]]
   [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(selmer.parser/set-resource-path! (clojure.java.io/resource "templates"))


(defroutes app-routes
           (GET "/" [] (render-file "home.html" (controllers/home-page)))
           (GET "/blog/:id" [id]  (render-file "blog.html" (controllers/blog-page id)))
           (GET "/new-blog" [] (render-file "new-blog.html" {}) )
           (POST "/new-blog" {params :params } (do (controllers/create-blog params)(redirect "/")))
           (route/resources "/")
           (route/not-found "Not Found"))

(def app (wrap-defaults app-routes (assoc-in site-defaults [:security :anti-forgery] false)))
