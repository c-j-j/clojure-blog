(ns cj-blog.controllers)

(defn home-page "home page" []
  {:blogs [{:title "Some Blog Title" :date "10-10-2015"}
           {:title "Some Other Title" :date "10-13-2015"}
           ]}
  )
