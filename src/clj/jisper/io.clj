(ns jisper.io
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn make-reader [path]
  (or (try (io/reader path)
           (catch Exception e))
      (if-let [r (io/resource path)]
        (io/reader r)
        (throw (RuntimeException. (str "could not find " path " neither in classpath nor as an absolute path"))))))

(defn csv-data->maps [path]
  (let [reader (-> path make-reader csv/read-csv)]
    (into []
      (map zipmap
           (->> (first reader) ;; header
                repeat)
           (rest reader)))))
