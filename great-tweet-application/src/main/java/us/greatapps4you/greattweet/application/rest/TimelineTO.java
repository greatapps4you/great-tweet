/*
 * GreatApps4you LLC Copyright (c) 2021
 * https://greatapps4you.us
 * CSSML NDSMD VRS + NSMV SMQL IVB
 */

package us.greatapps4you.greattweet.application.rest;

import java.time.LocalDateTime;

public final class TimelineTO {

    private final String uniqueName;
    private final String name;
    private final LocalDateTime publicationTime;
    private final String content;

    public TimelineTO(String uniqueName, String name, LocalDateTime publicationTime, String content) {
        this.uniqueName = uniqueName;
        this.name = name;
        this.publicationTime = publicationTime;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimelineTO that = (TimelineTO) o;

        if (!uniqueName.equals(that.uniqueName)) return false;
        if (!publicationTime.equals(that.publicationTime)) return false;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        int result = uniqueName.hashCode();
        result = 31 * result + publicationTime.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getPublicationTime() {
        return publicationTime;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "TimelineTO{" +
                "uniqueName='" + uniqueName + '\'' +
                ", name='" + name + '\'' +
                ", publicationTime=" + publicationTime +
                ", content='" + content + '\'' +
                '}';
    }
}
