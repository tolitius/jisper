(def +version+ "0.1.0-SNAPSHOT")

(set-env!
  :source-paths #{"src/clj" "src/java"}
  :dependencies '[[org.clojure/clojure      "1.10.1"]
                  [org.clojure/data.csv     "0.1.4"]

                  ;; boot clj
                  [boot/core                "2.7.1"      :scope "provided"]
                  [adzerk/bootlaces         "0.2.0"      :scope "test"]
                  [adzerk/boot-test         "1.2.0"      :scope "test"]])

(require '[adzerk.bootlaces :refer :all]
         '[adzerk.boot-test :as bt])

(bootlaces! +version+)

(defn uber-env []
  (set-env! :source-paths #(conj % "test"))
  (set-env! :resource-paths #(conj % "dev-resources"))
  (javac))

(deftask dev []
  (comp
    (uber-env)
    (javac)
    (aot)
    (repl)))

(deftask test []
  (comp
    (uber-env)
    (javac)
    (aot)
    (bt/test)))

(deftask build []
  (comp (pom)
        (javac)
        (aot)
        (jar)
        (install)))

(task-options!
  push {:ensure-branch nil}
  aot {:all true}
  jar {:manifest {"answer" "42"}}
  pom {:project     'tolitius/jisper
       :version     +version+
       :description "java friendly lisp whisper"
       :url         "https://github.com/tolitius/jisper"
       :scm         {:url "https://github.com/tolitius/jisper"}
       :license     {"Eclipse Public License"
                     "http://www.eclipse.org/legal/epl-v10.html"}})
