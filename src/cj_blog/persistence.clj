(ns cj-blog.persistence)

(def blogs (atom []))
(defn clear-blogs [] (reset! blogs []))
(defn add-blog [blog] (swap! blogs conj blog))
(defn get-all-blogs [] @blogs)
(defn get-blog[id] (first (filter #(= id (get % :id)) @blogs)))
