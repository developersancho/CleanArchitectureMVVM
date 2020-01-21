package com.developersancho.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repos(

    @SerializedName("archive_url")
    var archiveUrl: String? = null,

    @SerializedName("archived")
    var archived: Boolean = false,

    @SerializedName("assignees_url")
    var assigneesUrl: String? = null,

    @SerializedName("blobs_url")
    var blobsUrl: String? = null,

    @SerializedName("branches_url")
    var branchesUrl: String? = null,

    @SerializedName("clone_url")
    var cloneUrl: String? = null,

    @SerializedName("collaborators_url")
    var collaboratorsUrl: String? = null,

    @SerializedName("comments_url")
    var commentsUrl: String? = null,

    @SerializedName("commits_url")
    var commitsUrl: String? = null,

    @SerializedName("compare_url")
    var compareUrl: String? = null,

    @SerializedName("contents_url")
    var contentsUrl: String? = null,

    @SerializedName("contributors_url")
    var contributorsUrl: String? = null,

    @SerializedName("created_at")
    var createdAt: String? = null,

    @SerializedName("default_branch")
    var defaultBranch: String? = null,

    @SerializedName("deployments_url")
    var deploymentsUrl: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("disabled")
    var disabled: Boolean = false,

    @SerializedName("downloads_url")
    var downloadsUrl: String? = null,

    @SerializedName("events_url")
    var eventsUrl: String? = null,

    @SerializedName("fork")
    var fork: Boolean = false,

    @SerializedName("forks")
    var forks: Int = 0,

    @SerializedName("forks_count")
    var forksCount: Int = 0,

    @SerializedName("forks_url")
    var forksUrl: String? = null,

    @SerializedName("full_name")
    var fullName: String? = null,

    @SerializedName("git_commits_url")
    var gitCommitsUrl: String? = null,

    @SerializedName("git_refs_url")
    var gitRefsUrl: String? = null,

    @SerializedName("git_tags_url")
    var gitTagsUrl: String? = null,

    @SerializedName("git_url")
    var gitUrl: String? = null,

    @SerializedName("has_downloads")
    var hasDownloads: Boolean = false,

    @SerializedName("has_issues")
    var hasIssues: Boolean = false,

    @SerializedName("has_pages")
    var hasPages: Boolean = false,

    @SerializedName("has_projects")
    var hasProjects: Boolean = false,

    @SerializedName("has_wiki")
    var hasWiki: Boolean = false,

    @SerializedName("homepage")
    var homepage: String? = null,

    @SerializedName("hooks_url")
    var hooksUrl: String? = null,

    @SerializedName("html_url")
    var htmlUrl: String? = null,

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("issue_comment_url")
    var issueCommentUrl: String? = null,

    @SerializedName("issue_events_url")
    var issueEventsUrl: String? = null,

    @SerializedName("issues_url")
    var issuesUrl: String? = null,

    @SerializedName("keys_url")
    var keysUrl: String? = null,

    @SerializedName("labels_url")
    var labelsUrl: String? = null,

    @SerializedName("language")
    var language: String? = null,

    @SerializedName("languages_url")
    var languagesUrl: String? = null,

    @SerializedName("license")
    var license: License? = null,

    @SerializedName("merges_url")
    var mergesUrl: String? = null,

    @SerializedName("milestones_url")
    var milestonesUrl: String? = null,

    @SerializedName("mirror_url")
    var mirrorUrl: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("node_id")
    var nodeId: String? = null,

    @SerializedName("notifications_url")
    var notificationsUrl: String? = null,

    @SerializedName("open_issues")
    var openIssues: Int = 0,

    @SerializedName("open_issues_count")
    var openIssuesCount: Int = 0,

    @SerializedName("owner")
    var owner: Owner? = null,

    @SerializedName("private")
    var private: Boolean = false,

    @SerializedName("pulls_url")
    var pullsUrl: String? = null,

    @SerializedName("pushed_at")
    var pushedAt: String? = null,

    @SerializedName("releases_url")
    var releasesUrl: String? = null,

    @SerializedName("size")
    var size: Int = 0,

    @SerializedName("ssh_url")
    var sshUrl: String? = null,

    @SerializedName("stargazers_count")
    var stargazersCount: Int = 0,

    @SerializedName("stargazers_url")
    var stargazersUrl: String? = null,

    @SerializedName("statuses_url")
    var statusesUrl: String? = null,

    @SerializedName("subscribers_url")
    var subscribersUrl: String? = null,

    @SerializedName("subscription_url")
    var subscriptionUrl: String? = null,

    @SerializedName("svn_url")
    var svnUrl: String? = null,

    @SerializedName("tags_url")
    var tagsUrl: String? = null,

    @SerializedName("teams_url")
    var teamsUrl: String? = null,

    @SerializedName("trees_url")
    var treesUrl: String? = null,

    @SerializedName("updated_at")
    var updatedAt: String? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("watchers")
    var watchers: Int = 0,

    @SerializedName("watchers_count")
    var watchersCount: Int = 0,

    @Transient
    var isFav: Boolean = false

) : Serializable