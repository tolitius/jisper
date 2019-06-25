(ns jisper.core
  (:require [jisper.navigator :as nav]
            [jisper.io :as io])
  (:gen-class
    :name tolitius.Jisper
    :methods [^:static [getIn [Object java.util.List] Object]
              ^:static [csvToMap [String] java.util.List]]
    ))

(defn -getIn [m path]
  (nav/get-in m path))

(defn -csvToMap [path]
  (io/csv-data->maps path))
