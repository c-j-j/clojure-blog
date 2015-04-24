(defproject cj-blog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [ring/ring-anti-forgery "1.0.0"]
                 [clj-time "0.8.0"]
                 [selmer "0.8.2"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler cj-blog.handler/app :reload-paths "resources"}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]
                        [midje "1.6.3"]
                        ]}})
