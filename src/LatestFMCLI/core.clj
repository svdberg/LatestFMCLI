(ns LatestFMCLI.core
  (:import (net.roarsoftware.lastfm User)))

(def api_key "yourkey")

(def user "your user")

(defn weekly-artist-chart
    "get the chart of a user"
    [username nr-of-items the-key]
    (. User getWeeklyArtistChart username nr-of-items the-key ))

(defn top-tracks
  "get the top played tracks of a user"
  [username the-key]
  (. User getTopTracks username the-key))

(def artists
    (let [artlist (weekly-artist-chart user 10 api_key)]
          (. artlist getEntries)))

(defn show-artist-list
    []
    (print (map #(.getName %) artists)))

(defn print-track
  "Print a single track to a string"
  [track]
  (let [ track-name (. track getName)
         artist-name (. track getArtist)]
    (str track-name " by " artist-name)))

(defn number-a-sequence
  "append numbers to the items of a sequence, as str"
  [seek]
  (map-indexed #(str (+ 1 %1) " " %2) seek))

(defn print-top-tracks
  "print the list of most played tracks"
  []
  (let [track-list (top-tracks user api_key)]
    (map #(print-track %) track-list)))

(defn pretty-print
  [top-list]
  (let [header "<html><body>"
        footer "</body></html>"]
    (str header (reduce str (map #(str % "<br/>") top-list )) footer)))

(defn- main [args]
  (pretty-print (number-a-sequence (print-top-tracks))))
