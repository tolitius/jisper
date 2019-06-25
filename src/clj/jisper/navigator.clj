(ns jisper.navigator
  (:refer-clojure :exclude [get-in]))

(defn jcol->ccol [xs]
  (cond
    (instance? java.util.List xs) (vec xs)
    :default xs))

(defn get-in
  "same as clojure.core/get-in with java.util.List support"
  ([m ks]
   (let [m (jcol->ccol m)]
     (reduce (comp jcol->ccol get) m ks)))
  ([m ks not-found]
   (loop [sentinel (Object.)
          m (jcol->ccol m)
          ks (seq ks)]
     (if ks
       (let [m (get m (first ks) sentinel)]
         (if (identical? sentinel m)
           not-found
           (recur sentinel (jcol->ccol m) (next ks))))
       m))))
