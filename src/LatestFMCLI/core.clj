(ns LatestFMCLI.core
  (:import (net.roarsoftware.lastfm User)))

(def api_key "yourkey")

(def user "auser")

(defn weekly-artist-chart
    "get the chart of a user"
    [username nr-of-items the-key]
    (. User getWeeklyArtistChart username nr-of-items the-key ))

(def artists
    (let [artlist (weekly-artist-chart user 10 api_key)]
          (. artlist getEntries)))

(defn show-artist-list
    []
    (print (map #(.getName %) artists)))
