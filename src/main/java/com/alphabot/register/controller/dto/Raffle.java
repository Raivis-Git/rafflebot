package com.alphabot.register.controller.dto;

import java.util.List;

public class Raffle {

    String _id;
    String slug;
    String name;
    String type;
    String status;
    String visibility;
    String description;
    Long startDate;
    Long endDate;
    Long winnerCount;
    String bannerImageUrl;
    String blockchain;
    String twitterUrl;
    String discordUrl;
    Long entryCount;
    String reqString;
    String projectId;
    String teamId;
    Boolean dtc;
    Boolean requirePremium;
    Boolean connectDiscord;
    Boolean connectTwitter;
    Boolean connectWallet;
    Boolean connectEmail;
    Boolean connectTelegram;
    Boolean connectPassword;
    Boolean connectCaptcha;
    Boolean signWallet;
    Boolean excludePreviousWinners;
    Long requiredEth;
    List<RequiredTokens> requiredTokens;
    List<DiscordServerRoles> discordServerRoles;
    List<TwitterFollows> twitterFollows;
    String twitterRetweet;
    String twitterRetweetType;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Long getWinnerCount() {
        return winnerCount;
    }

    public void setWinnerCount(Long winnerCount) {
        this.winnerCount = winnerCount;
    }

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }

    public String getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getDiscordUrl() {
        return discordUrl;
    }

    public void setDiscordUrl(String discordUrl) {
        this.discordUrl = discordUrl;
    }

    public Long getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(Long entryCount) {
        this.entryCount = entryCount;
    }

    public String getReqString() {
        return reqString;
    }

    public void setReqString(String reqString) {
        this.reqString = reqString;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Boolean getDtc() {
        return dtc;
    }

    public void setDtc(Boolean dtc) {
        this.dtc = dtc;
    }

    public Boolean getRequirePremium() {
        return requirePremium;
    }

    public void setRequirePremium(Boolean requirePremium) {
        this.requirePremium = requirePremium;
    }

    public Boolean getConnectDiscord() {
        return connectDiscord;
    }

    public void setConnectDiscord(Boolean connectDiscord) {
        this.connectDiscord = connectDiscord;
    }

    public Boolean getConnectTwitter() {
        return connectTwitter;
    }

    public void setConnectTwitter(Boolean connectTwitter) {
        this.connectTwitter = connectTwitter;
    }

    public Boolean getConnectWallet() {
        return connectWallet;
    }

    public void setConnectWallet(Boolean connectWallet) {
        this.connectWallet = connectWallet;
    }

    public Boolean getConnectEmail() {
        return connectEmail;
    }

    public void setConnectEmail(Boolean connectEmail) {
        this.connectEmail = connectEmail;
    }

    public Boolean getConnectTelegram() {
        return connectTelegram;
    }

    public void setConnectTelegram(Boolean connectTelegram) {
        this.connectTelegram = connectTelegram;
    }

    public Boolean getConnectPassword() {
        return connectPassword;
    }

    public void setConnectPassword(Boolean connectPassword) {
        this.connectPassword = connectPassword;
    }

    public Boolean getConnectCaptcha() {
        return connectCaptcha;
    }

    public void setConnectCaptcha(Boolean connectCaptcha) {
        this.connectCaptcha = connectCaptcha;
    }

    public Boolean getSignWallet() {
        return signWallet;
    }

    public void setSignWallet(Boolean signWallet) {
        this.signWallet = signWallet;
    }

    public Boolean getExcludePreviousWinners() {
        return excludePreviousWinners;
    }

    public void setExcludePreviousWinners(Boolean excludePreviousWinners) {
        this.excludePreviousWinners = excludePreviousWinners;
    }

    public Long getRequiredEth() {
        return requiredEth;
    }

    public void setRequiredEth(Long requiredEth) {
        this.requiredEth = requiredEth;
    }

    public List<RequiredTokens> getRequiredTokens() {
        return requiredTokens;
    }

    public void setRequiredTokens(List<RequiredTokens> requiredTokens) {
        this.requiredTokens = requiredTokens;
    }

    public List<DiscordServerRoles> getDiscordServerRoles() {
        return discordServerRoles;
    }

    public void setDiscordServerRoles(List<DiscordServerRoles> discordServerRoles) {
        this.discordServerRoles = discordServerRoles;
    }

    public List<TwitterFollows> getTwitterFollows() {
        return twitterFollows;
    }

    public void setTwitterFollows(List<TwitterFollows> twitterFollows) {
        this.twitterFollows = twitterFollows;
    }

    public String getTwitterRetweet() {
        return twitterRetweet;
    }

    public void setTwitterRetweet(String twitterRetweet) {
        this.twitterRetweet = twitterRetweet;
    }

    public String getTwitterRetweetType() {
        return twitterRetweetType;
    }

    public void setTwitterRetweetType(String twitterRetweetType) {
        this.twitterRetweetType = twitterRetweetType;
    }
}
