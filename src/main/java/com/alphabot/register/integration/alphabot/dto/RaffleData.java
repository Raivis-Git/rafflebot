package com.alphabot.register.integration.alphabot.dto;

import com.alphabot.register.integration.alphabot.enums.Status;

public class RaffleData {
    String _id;
    String bannerImageUrl;
    String blockchain;
    Boolean connectUrl;
    Boolean connectWallet;
    String discordUrl;
    String endDate;
    Boolean isProjectTeam;
    Boolean isTeamHidden;
    Boolean mintDateHasTime;
    String name;
    Long picked;
    String reqString;
    Boolean requirePremium;
    String slug;
    String startDate;
    Status status; //enum
    String supply;
    String twitterUrl;
    String type; // enum
    String weblinkUrl;
    String weblinkUrlTitle;
    Integer winnerCount;
    Boolean dtc;
    String mintDate;
    Integer entryCount;
    Boolean isRegistered;
    Boolean isWriteAccess;
    Boolean isHidden;
    Boolean isWinner;
    Boolean isWinConfirmed;
    Boolean isLost;
    Boolean isPending;
    Boolean isUnregistered;
    Boolean isPendingConfirm;
    Integer taskCount;
    String teamId;
    String projectId;

    public String get_id() {
        return _id;
    }

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public String getBlockchain() {
        return blockchain;
    }

    public Boolean getConnectUrl() {
        return connectUrl;
    }

    public Boolean getConnectWallet() {
        return connectWallet;
    }

    public String getDiscordUrl() {
        return discordUrl;
    }

    public String getEndDate() {
        return endDate;
    }

    public Boolean getProjectTeam() {
        return isProjectTeam;
    }

    public Boolean getTeamHidden() {
        return isTeamHidden;
    }

    public Boolean getMintDateHasTime() {
        return mintDateHasTime;
    }

    public String getName() {
        return name;
    }

    public Long getPicked() {
        return picked;
    }

    public String getReqString() {
        return reqString;
    }

    public Boolean getRequirePremium() {
        return requirePremium;
    }

    public String getSlug() {
        return slug;
    }

    public String getStartDate() {
        return startDate;
    }

    public Status getStatus() {
        return status;
    }

    public String getSupply() {
        return supply;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public String getType() {
        return type;
    }

    public String getWeblinkUrl() {
        return weblinkUrl;
    }

    public String getWeblinkUrlTitle() {
        return weblinkUrlTitle;
    }

    public Integer getWinnerCount() {
        return winnerCount;
    }

    public Boolean getDtc() {
        return dtc;
    }

    public String getMintDate() {
        return mintDate;
    }

    public Integer getEntryCount() {
        return entryCount;
    }

    public Boolean getRegistered() {
        return isRegistered;
    }

    public Boolean getWriteAccess() {
        return isWriteAccess;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public Boolean getWinner() {
        return isWinner;
    }

    public Boolean getWinConfirmed() {
        return isWinConfirmed;
    }

    public Boolean getLost() {
        return isLost;
    }

    public Boolean getPending() {
        return isPending;
    }

    public Boolean getUnregistered() {
        return isUnregistered;
    }

    public Boolean getPendingConfirm() {
        return isPendingConfirm;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getProjectId() {
        return projectId;
    }
}
