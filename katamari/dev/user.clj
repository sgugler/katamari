(ns user
  (:require [me.raynes.fs :as fs]
            [roll.reader]))

(def +root+
  (str (System/getenv "HOME") "/doc/dat/git/arrdem/katamari"))

(def +conf+
  (merge (katamari.conf/load (str +root+ "/kat.conf")
                             katamari.server.web-server/key-fn)
         {:repo-root +root+}))

(def +graph+
  (roll.reader/compute-buildgraph +conf+))

(def +cache+
  (roll.cache/->buildcache
   (fs/file (:repo-root +conf+)
            (:server-work-dir +conf+)
            (:server-build-cache +conf+))))
