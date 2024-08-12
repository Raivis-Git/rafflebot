package com.alphabot.register.integration.alphabot.dto;

import java.util.List;

public class RegisterData {
    String resultMd;
    Validation validation;
    PendingCheck pendingCheck;

    public String getValidationReason() {
        return getValidation().getReason();
    }

    public String getResultMd() {
        return resultMd;
    }

    public void setResultMd(String resultMd) {
        this.resultMd = resultMd;
    }

    public Validation getValidation() {
        return validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    public PendingCheck getPendingCheck() {
        return pendingCheck;
    }

    public void setPendingCheck(PendingCheck pendingCheck) {
        this.pendingCheck = pendingCheck;
    }

    class Validation {
        Long entries;
        Boolean discordRefresh;
        Boolean discordValid;
        Boolean twitterValid;
        Boolean tokensValid;
        List<Boolean> discordValidations;
        List<Boolean> twitterValidation;
        List<Boolean> tokensValidations;
        List<Boolean> telegramValidations;
        Boolean passwordInvalid;
        Boolean ethBalanceInvalid;
        Boolean questionsValid;
        List<Boolean> questionValidations;
        Boolean appCodeInvalid;
        Boolean emailValid;
        Boolean success;
        String reason;

        public Long getEntries() {
            return entries;
        }

        public void setEntries(Long entries) {
            this.entries = entries;
        }

        public Boolean getDiscordRefresh() {
            return discordRefresh;
        }

        public void setDiscordRefresh(Boolean discordRefresh) {
            this.discordRefresh = discordRefresh;
        }

        public Boolean getDiscordValid() {
            return discordValid;
        }

        public void setDiscordValid(Boolean discordValid) {
            this.discordValid = discordValid;
        }

        public Boolean getTwitterValid() {
            return twitterValid;
        }

        public void setTwitterValid(Boolean twitterValid) {
            this.twitterValid = twitterValid;
        }

        public Boolean getTokensValid() {
            return tokensValid;
        }

        public void setTokensValid(Boolean tokensValid) {
            this.tokensValid = tokensValid;
        }

        public List<Boolean> getDiscordValidations() {
            return discordValidations;
        }

        public void setDiscordValidations(List<Boolean> discordValidations) {
            this.discordValidations = discordValidations;
        }

        public List<Boolean> getTwitterValidation() {
            return twitterValidation;
        }

        public void setTwitterValidation(List<Boolean> twitterValidation) {
            this.twitterValidation = twitterValidation;
        }

        public List<Boolean> getTokensValidations() {
            return tokensValidations;
        }

        public void setTokensValidations(List<Boolean> tokensValidations) {
            this.tokensValidations = tokensValidations;
        }

        public List<Boolean> getTelegramValidations() {
            return telegramValidations;
        }

        public void setTelegramValidations(List<Boolean> telegramValidations) {
            this.telegramValidations = telegramValidations;
        }

        public Boolean getPasswordInvalid() {
            return passwordInvalid;
        }

        public void setPasswordInvalid(Boolean passwordInvalid) {
            this.passwordInvalid = passwordInvalid;
        }

        public Boolean getEthBalanceInvalid() {
            return ethBalanceInvalid;
        }

        public void setEthBalanceInvalid(Boolean ethBalanceInvalid) {
            this.ethBalanceInvalid = ethBalanceInvalid;
        }

        public Boolean getQuestionsValid() {
            return questionsValid;
        }

        public void setQuestionsValid(Boolean questionsValid) {
            this.questionsValid = questionsValid;
        }

        public List<Boolean> getQuestionValidations() {
            return questionValidations;
        }

        public void setQuestionValidations(List<Boolean> questionValidations) {
            this.questionValidations = questionValidations;
        }

        public Boolean getAppCodeInvalid() {
            return appCodeInvalid;
        }

        public void setAppCodeInvalid(Boolean appCodeInvalid) {
            this.appCodeInvalid = appCodeInvalid;
        }

        public Boolean getEmailValid() {
            return emailValid;
        }

        public void setEmailValid(Boolean emailValid) {
            this.emailValid = emailValid;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
    class PendingCheck {
        Integer start;
        Integer complete;
        Completed completed;

        public Integer getStart() {
            return start;
        }

        public void setStart(Integer start) {
            this.start = start;
        }

        public Integer getComplete() {
            return complete;
        }

        public void setComplete(Integer complete) {
            this.complete = complete;
        }

        public Completed getCompleted() {
            return completed;
        }

        public void setCompleted(Completed completed) {
            this.completed = completed;
        }

        class Completed {
            Integer rt;
            Integer follow;
            Long nft;
            Integer discord;
            Integer email;
            Integer telegram;

            public Integer getRt() {
                return rt;
            }

            public void setRt(Integer rt) {
                this.rt = rt;
            }

            public Integer getFollow() {
                return follow;
            }

            public void setFollow(Integer follow) {
                this.follow = follow;
            }

            public Long getNft() {
                return nft;
            }

            public void setNft(Long nft) {
                this.nft = nft;
            }

            public Integer getDiscord() {
                return discord;
            }

            public void setDiscord(Integer discord) {
                this.discord = discord;
            }

            public Integer getEmail() {
                return email;
            }

            public void setEmail(Integer email) {
                this.email = email;
            }

            public Integer getTelegram() {
                return telegram;
            }

            public void setTelegram(Integer telegram) {
                this.telegram = telegram;
            }
        }
    }
}
