/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTests;

import model.*;
import cgit.*;
import java.util.LinkedList;
import java.util.List;

public class cgitTests {
    
    public static void main(String [] args)
    {
        String ADir = "/Volumes/DATA/Users/andrewjorgensen/temp/QDA2";
        String BDir = "/Volumes/DATA/Users/andrewjorgensen/temp/qda_project";
        
        cgitTests.ADirMergeCommentsTest(ADir);
        cgitTests.BDirMergeCommentsTest(BDir);
        
        cgitTests.ADirMergeTagsTest(ADir);
        cgitTests.BDirMergeTagsTest(BDir);
        cgitTests.mergeAandB(ADir, BDir);
    }
    
    private static void ADirMergeCommentsTest(String working_dir)
    {
        MyLogger.LogMessageToConsole(cgitTests.class, "ADirMergeTest", LogType.DEBUG);
        setup.setup_qda_directory(working_dir);
        
        
        SourceText s1 = new SourceText("/Volumes/DATA/Users/andrewjorgensen/temp/QDA2/SourceDoc.txt");
        SourceText s2 = new SourceText("/Volumes/DATA/Users/andrewjorgensen/temp/QDA2/SourceDoc1.txt");
        
        //Four Comments
        Comment c1 = Comment.generateNewComment("aj", new TextSection(0,10), "hello", s1.getContentHash());
        Comment c2 = Comment.generateNewComment("aj", new TextSection(0,10), "one", s1.getContentHash());
        Comment c3 = Comment.generateNewComment("aj", new TextSection(0,10), "two", s2.getContentHash());
        Comment c4 = Comment.generateNewComment("aj", new TextSection(0,10), "three", s2.getContentHash());
        
        List<Comment> a1 = new LinkedList<Comment>();
        a1.add(c1);
        a1.add(c2);
        
        comments.saveComments(working_dir, a1);
        
        Branch.commit(working_dir, "aj", "first commit");
        
        List<Comment> a2 = new LinkedList<Comment>();

        //Second State
        a2.add(c3);
        a2.add(c4);
        comments.saveComments(working_dir, a2);
        Branch.commit(working_dir, "aj", "second commit");
        
    }
    
    private static void BDirMergeCommentsTest(String working_dir)
    {
        MyLogger.LogMessageToConsole(cgitTests.class, "BDirMergeTest", LogType.DEBUG);
        setup.setup_qda_directory(working_dir);
        
        SourceText s1 = new SourceText("/Volumes/DATA/Users/andrewjorgensen/temp/QDA2/SourceDoc.txt");
        
        //three comments
        Comment c1 = Comment.generateNewComment("aj", new TextSection(0,10), "hello", s1.getContentHash());
        Comment c2 = Comment.generateNewComment("aj", new TextSection(0,10), "three", s1.getContentHash());
        Comment c3 = Comment.generateNewComment("aj", new TextSection(0,10), "five", s1.getContentHash());
        
        List<Comment> b1 = new LinkedList<Comment>();
        b1.add(c1);
        b1.add(c2);
        b1.add(c3);
        
        comments.saveComments(working_dir, b1);
        Branch.commit(working_dir, "aj", "first commit");
    }
    
    private static void ADirMergeTagsTest(String working_dir)
    {
        MyLogger.LogMessageToConsole(cgitTests.class, "ADirMergeTest", LogType.DEBUG);
        setup.setup_qda_directory(working_dir);
        
        
        SourceText s1 = new SourceText("/Volumes/DATA/Users/andrewjorgensen/temp/QDA2/SourceDoc.txt");
        SourceText s2 = new SourceText("/Volumes/DATA/Users/andrewjorgensen/temp/QDA2/SourceDoc1.txt");
        
        //Four Comments        
        TagInstance t1 = TagInstance.generateNewTag("aj", new TextSection(0,10), new TagType("Tag1"), s1.getContentHash());
        TagInstance t2 = TagInstance.generateNewTag("aj", new TextSection(0,10), new TagType("Tag2"), s1.getContentHash());
        TagInstance t3 = TagInstance.generateNewTag("aj", new TextSection(0,10), new TagType("Tag3"), s1.getContentHash());
        TagInstance t4 = TagInstance.generateNewTag("aj", new TextSection(0,10), new TagType("Tag4"), s1.getContentHash());
        
        List<TagInstance> a1 = new LinkedList<TagInstance>();
        a1.add(t1);
        a1.add(t2);
        
        tags.saveTags(working_dir, a1);
        
        Branch.commit(working_dir, "aj", "first commit");
        
        List<TagInstance> a2 = new LinkedList<TagInstance>();

        //Second State
        a2.add(t3);
        a2.add(t4);
        tags.saveTags(working_dir, a2);
        
        Branch.commit(working_dir, "aj", "second commit");
    }
    
    private static void BDirMergeTagsTest(String working_dir)
    {
        MyLogger.LogMessageToConsole(cgitTests.class, "BDirMergeTest", LogType.DEBUG);
        setup.setup_qda_directory(working_dir);
        
        SourceText s1 = new SourceText("/Volumes/DATA/Users/andrewjorgensen/temp/QDA2/SourceDoc.txt");
        
        //three comments
        TagInstance t1 = TagInstance.generateNewTag("aj", new TextSection(0,10), new TagType("Tag1"), s1.getContentHash());
        TagInstance t2 = TagInstance.generateNewTag("aj", new TextSection(0,10), new TagType("Tag2"), s1.getContentHash());
        TagInstance t3 = TagInstance.generateNewTag("aj", new TextSection(0,10), new TagType("Tag3"), s1.getContentHash());
        
        List<TagInstance> b1 = new LinkedList<TagInstance>();
        b1.add(t1);
        b1.add(t2);
        b1.add(t3);
        
        tags.saveTags(working_dir, b1);
        Branch.commit(working_dir, "aj", "first commit");   
    }
    
    private static void mergeAandB(String A, String B)
    {
        MyLogger.LogMessageToConsole(cgitTests.class, "mergeAandB", LogType.DEBUG);
        List<Comment> mergedComments = Branch.mergeProjectsComments(A, B, new SourceText("/Volumes/DATA/Users/andrewjorgensen/temp/QDA2/SourceDoc.txt"));
        List<TagInstance> mergedTags = Branch.mergeProjectTags(A, B, new SourceText("/Volumes/DATA/Users/andrewjorgensen/temp/QDA2/SourceDoc.txt"));
        
        
        //save the comments to disk
        comments.saveComments(B, mergedComments);
        tags.saveTags(B, mergedTags);
        
        for(Comment comment : mergedComments)
        {
            System.out.println(comment.getOwner() + " " + comment.getComment());
        }
        
        System.out.println("");
        
        for(TagInstance tag : mergedTags)
        {
            System.out.println(tag.getOwner() + " " + tag.getTagType().getName());
        }
    }
}
