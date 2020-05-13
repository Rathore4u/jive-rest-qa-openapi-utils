package com.jive.automation.test;

import com.google.gson.Gson;
import com.jive.restapi.generated.person.JSON;
import lombok.RequiredArgsConstructor;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

public class SerializationTests {
    private static Class[] models = new Class[] {
            com.jive.restapi.generated.person.models.AbuseReport.class,
            com.jive.restapi.generated.person.models.ActionLink.class,
            com.jive.restapi.generated.person.models.Activity.class,
            com.jive.restapi.generated.person.models.ActivityObject.class,
            com.jive.restapi.generated.person.models.AddOn.class,
            com.jive.restapi.generated.person.models.Address.class,
            com.jive.restapi.generated.person.models.AddressValue.class,
            com.jive.restapi.generated.person.models.Announcement.class,
            com.jive.restapi.generated.person.models.AppliedEntitlement.class,
            com.jive.restapi.generated.person.models.Attachment.class,
            com.jive.restapi.generated.person.models.Blog.class,
            com.jive.restapi.generated.person.models.Category.class,
            com.jive.restapi.generated.person.models.Comment.class,
            com.jive.restapi.generated.person.models.Content.class,
            com.jive.restapi.generated.person.models.ContentBanner.class,
            com.jive.restapi.generated.person.models.ContentBody.class,
            com.jive.restapi.generated.person.models.ContentEntities.class,
            com.jive.restapi.generated.person.models.ContentTag.class,
            com.jive.restapi.generated.person.models.ContentVideo.class,
            com.jive.restapi.generated.person.models.Deprecation.class,
            com.jive.restapi.generated.person.models.DirectMessage.class,
            com.jive.restapi.generated.person.models.Discussion.class,
            com.jive.restapi.generated.person.models.DiscussionEntities.class,
            com.jive.restapi.generated.person.models.Document.class,
            com.jive.restapi.generated.person.models.DocumentEntities.class,
            com.jive.restapi.generated.person.models.Email.class,
            com.jive.restapi.generated.person.models.Embedded.class,
            com.jive.restapi.generated.person.models.Entities.class,
            com.jive.restapi.generated.person.models.Entitlement.class,
            com.jive.restapi.generated.person.models.Event.class,
            com.jive.restapi.generated.person.models.EventCategoryType.class,
            com.jive.restapi.generated.person.models.ExternalIdentity.class,
            com.jive.restapi.generated.person.models.ExternalStreamDefinition.class,
            com.jive.restapi.generated.person.models.ExternalStreamInstance.class,
            com.jive.restapi.generated.person.models.Favorite.class,
            com.jive.restapi.generated.person.models.Field.class,
            com.jive.restapi.generated.person.models.FileModel.class,
            com.jive.restapi.generated.person.models.Group.class,
            com.jive.restapi.generated.person.models.HeaderSettings.class,
            com.jive.restapi.generated.person.models.Idea.class,
            com.jive.restapi.generated.person.models.Image.class,
            com.jive.restapi.generated.person.models.InboxEntry.class,
            com.jive.restapi.generated.person.models.InlineObject.class,
            com.jive.restapi.generated.person.models.InlineObject1.class,
            com.jive.restapi.generated.person.models.Jive.class,
            com.jive.restapi.generated.person.models.JiveExtension.class,
            com.jive.restapi.generated.person.models.Level.class,
            com.jive.restapi.generated.person.models.Links.class,
            com.jive.restapi.generated.person.models.MediaLink.class,
            com.jive.restapi.generated.person.models.Message.class,
            com.jive.restapi.generated.person.models.ModelStatic.class,
            com.jive.restapi.generated.person.models.Name.class,
            com.jive.restapi.generated.person.models.News.class,
            com.jive.restapi.generated.person.models.NewsStream.class,
            com.jive.restapi.generated.person.models.OnBehalfOf.class,
            com.jive.restapi.generated.person.models.OpenSocial.class,
            com.jive.restapi.generated.person.models.Outcome.class,
            com.jive.restapi.generated.person.models.OutcomeWithApprovers.class,
            com.jive.restapi.generated.person.models.OutcomeType.class,
            com.jive.restapi.generated.person.models.Page.class,
            com.jive.restapi.generated.person.models.Person.class,
            com.jive.restapi.generated.person.models.PersonActivityObject.class,
            com.jive.restapi.generated.person.models.PersonRoles.class,
            com.jive.restapi.generated.person.models.PhoneNumber.class,
            com.jive.restapi.generated.person.models.Photo.class,
            com.jive.restapi.generated.person.models.Place.class,
            com.jive.restapi.generated.person.models.PlaceTemplate.class,
            com.jive.restapi.generated.person.models.PlaceTemplateCategory.class,
            com.jive.restapi.generated.person.models.PlaceTopic.class,
            com.jive.restapi.generated.person.models.Poll.class,
            com.jive.restapi.generated.person.models.PollOptionImage.class,
            com.jive.restapi.generated.person.models.Post.class,
            com.jive.restapi.generated.person.models.PrivateProps.class,
            com.jive.restapi.generated.person.models.Profile.class,
            com.jive.restapi.generated.person.models.ProfileField.class,
            com.jive.restapi.generated.person.models.ProfileFieldOption.class,
            com.jive.restapi.generated.person.models.ProfileFieldPrivacy.class,
            com.jive.restapi.generated.person.models.ProfileFieldText.class,
            com.jive.restapi.generated.person.models.ProfileImage.class,
            com.jive.restapi.generated.person.models.PublishError.class,
            com.jive.restapi.generated.person.models.Resource.class,
            com.jive.restapi.generated.person.models.RoleBadge.class,
            com.jive.restapi.generated.person.models.SecurityGroup.class,
            com.jive.restapi.generated.person.models.Settings.class,
            com.jive.restapi.generated.person.models.Slide.class,
            com.jive.restapi.generated.person.models.Stream.class,
            com.jive.restapi.generated.person.models.Summary.class,
            com.jive.restapi.generated.person.models.Task.class,
            com.jive.restapi.generated.person.models.TileCategory.class,
            com.jive.restapi.generated.person.models.TileDefinition.class,
            com.jive.restapi.generated.person.models.TileInstance.class,
            com.jive.restapi.generated.person.models.Update.class,
            com.jive.restapi.generated.person.models.Via.class,
            com.jive.restapi.generated.person.models.Checkpoint.class,
            com.jive.restapi.generated.person.models.Project.class,
            com.jive.restapi.generated.person.models.SetCheckpoint.class,
            com.jive.restapi.generated.person.models.SetCheckpoints.class,
            com.jive.restapi.generated.person.models.EventInvite.class,
            com.jive.restapi.generated.person.models.Invite.class,
            com.jive.restapi.generated.person.models.InviteRequest.class
    };

    @Test
    public void modelShouldWork() throws Throwable {
        try {
            Stream.of(models)
                    .map(this::testSerialize)
                    .forEach((Consumer<Pair<?>>)(p)->
                            assertThat(p.clone, IsEqual.equalTo(p.original)));
        } catch (RuntimeException e) {
            throw e.getCause();
        }
    }


    @RequiredArgsConstructor
    public static class Pair<T> {
        final T original;
        final T clone;
    }

    private <T> Pair<T> testSerialize(Class<T> aClass) {
        try {
            Gson gson = JSON.createGson().create();
            T instance = aClass.newInstance();
            String serialized = gson.toJson(instance);
            T other = gson.fromJson(serialized,aClass);
            return new Pair<>(instance,other);
        } catch (Exception e)  {
            throw new RuntimeException(e);
        }
    }
}
