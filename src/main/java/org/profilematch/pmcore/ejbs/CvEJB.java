package org.profilematch.pmcore.ejbs;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.profilematch.pmcore.entities.Candidat;
import org.profilematch.pmcore.entities.Competence;
import org.profilematch.pmcore.entities.ExperiencePro;
import org.profilematch.pmcore.entities.Formation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;

/**
 * Created by antoine on 3/5/17.
 */
@Stateless
public class CvEJB {

    @EJB
    CandidatEJB candidatEJB;

    private Candidat c;
    private Document document;

    public File getCV(Long id){
        this.c = candidatEJB.getUser(id);

        String name = "cv_" + c.getNom()+".pdf";
        this.document = new Document();

        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(name));

            document.open();

            document.addAuthor(c.getNom());

            infoPersos();
            addPhoto();
            document.add(new Chunk(new LineSeparator()));
            document.add(new Paragraph(" "));
            addFormations();
            document.add(new Paragraph(" "));
            addExperiences();
            document.add(new Paragraph(" "));
            addCompetence();


            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new File(name);
    }

    private void addExperiences() throws DocumentException {
        List Experiences = new List(true,30);

        for(ExperiencePro exp : c.getExperiencePro()){
            Experiences.add(new ListItem(exp.getIntitule_de_poste()));
        }

        Chunk SectionExperiences  = new Chunk("Experiences\n");

        if(c.getExperiencePro().size() > 0){
            this.document.add(SectionExperiences);
            this.document.add(Experiences);
        }

    }

    private  void addFormations() throws DocumentException {
        List Formations = new List(true,30);

        for(Formation formation : c.getFormation()){
            Formations.add(new ListItem(formation.getIntitule_de_formation()));
        }


        Chunk SectionFormations = new Chunk("Formations\n");
        if(c.getFormation().size() > 0) {
            this.document.add(SectionFormations);
            this.document.add(Formations);
        }

    }

    private void addCompetence () throws DocumentException {
        List Competences = new List(true,30);

        for(Competence comp : c.getCompetence()){
            Competences.add(new ListItem(comp.getCompetence()));
        }

        Chunk SectionCompetences = new Chunk("Competences\n");

        if(c.getCompetence().size() > 0){
            this.document.add(SectionCompetences);
            this.document.add(Competences);
        }
    }

    private void addPhoto() throws IOException, DocumentException {

        if(c.getPhoto() != null) {
            String imagepath = "images/" + c.getPhoto();
            Image img = Image.getInstance(imagepath);
            img.scaleAbsolute(120f, 120f);// width,height of image in float
            img.setAbsolutePosition(PageSize.A4.getWidth() - img.getScaledWidth() - 39, PageSize.A4.getHeight() - img.getScaledHeight() - 20);

            this.document.add(img);
        }
    }

    private PdfPCell getCell(Paragraph p, int alignment) {
        PdfPCell cell = new PdfPCell(p);
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private void infoPersos() throws IOException, DocumentException {
        Paragraph p = new Paragraph();
        Font fontTitle=new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.WHITE);
        Chunk title=new Chunk(c.getNom() + " " + c.getPrenom() + "\n", fontTitle);
        title.setBackground(new BaseColor(0,30,255), 1f, 1f, 1f, 3f);
        title.setLineHeight(30f);
        //title.setUnderline(BaseColor.BLACK,5f,0.5f,2f,0.5f, PdfContentByte.LINE_CAP_ROUND);

        Paragraph p2 = new Paragraph();
        p2.add("\n");
        if(c.getAdresse() != null)
            p2.add(c.getAdresse() + "\n");
        if(c.getEmail() != null)
            p2.add(c.getEmail() + "\n");
        if(c.getTelperso() != null)
            p2.add(c.getTelperso()+ "\n");
        if(c.getTelfix() != null)
            p2.add(c.getTelfix()+ "\n");
        if(c.getNaissance() != null){
            p2.add((Calendar.getInstance().get(Calendar.YEAR) - c.getNaissance().get(Calendar.YEAR)) + "ans");
        }
        
        p.add(title);
        p.add(p2);

        this.document.add(p);
    }
}
