package com.studiario.ilmiostudiario.controller;



import com.studiario.ilmiostudiario.Utils;
import com.studiario.ilmiostudiario.model.Argomento;
import com.studiario.ilmiostudiario.model.Nozione;
import com.studiario.ilmiostudiario.model.UnitaApprendimento;
import com.studiario.ilmiostudiario.repository.NozioneRepository;
import com.studiario.ilmiostudiario.repository.UnitaApprendimentoRepository;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import com.studiario.ilmiostudiario.repository.ArgomentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/argomenti")
public class ArgomentoController {
    @Autowired
    // dependency injection: quando si crea un oggetto di tipo BookController ha bisogno di un BookRepository
    private ArgomentoRepository argomentoRepository;

    @Autowired
    private UnitaApprendimentoRepository unitaApprendimentoRepository;

    @Autowired
    private NozioneRepository nozioneRepository;

    @GetMapping
    public String index(@RequestParam(name = "keyword") Optional<String> searchKeyword, Model model) {
        //List<Argomento> argomentiList = argomentoRepository.findAll(); // questa è la lista di libri presa da database
        List<Argomento> argomentiList ;
        // preparo la variabile con il valore con cui precaricare l'input di ricerca
        String keyword = "";
        // verifico se ho la stringa di ricerca
        if (searchKeyword.isPresent()) {
            keyword = searchKeyword.get();
            // devo usare il metodo del repository che fa la ricerca filtrata
            argomentiList = argomentoRepository.findByNomeContaining(keyword);
        } else {
            // recupero tutti gli User da database
            argomentiList = argomentoRepository.findAll();
        }
        model.addAttribute("argomenti", argomentiList); // passo la lista di libri al model
        // passo anche l'attributo keyword con la chiave di ricerca
        model.addAttribute("keyword", keyword);
        return "argomenti/list";
    }

    // funzione di controllo delle unità di apprendimento --> l' url dello specifico argomento
    @GetMapping("/{slug}")
    public String show(@RequestParam(name = "keyword") Optional<String> searchKeyword,@PathVariable("slug") String slug, Model model) {

        Optional<Argomento> bookOptional = argomentoRepository.findBySlug(slug);
        // se nell'optional c'è il book proseguo e lo passo alla view
        if (bookOptional.isPresent()) {
            Argomento argomentoFromDB = bookOptional.get(); // chiedo all'optional di darmi il suo contenuto
            model.addAttribute("argomento", argomentoFromDB);
            //if keywoed
            List<UnitaApprendimento> unitaApprendimentoList;
            String keyword = "";
            if (searchKeyword.isPresent()) {
                keyword = searchKeyword.get();
                // devo usare il metodo del repository che fa la ricerca filtrata
                unitaApprendimentoList = unitaApprendimentoRepository.findByArgomentiAndNomeContaining(argomentoFromDB,keyword);
            } else {
                // recupero tutti gli User da database
                unitaApprendimentoList = unitaApprendimentoRepository.findUnitaApprendimentoByArgomenti(argomentoFromDB);;
            }
            model.addAttribute("units", unitaApprendimentoList);
            model.addAttribute("keyword", keyword);
            //List<UnitaApprendimento> unitaApprendimentoList= unitaApprendimentoRepository.findUnitaApprendimentoByArgomenti(argomentoFromDB);
           // List<UnitaArgomenti> unitaArgomentiList= unitaArgomentiRepository.findByArgomenti(argomentoFromDB);
           // model.addAttribute("units",unitaArgomentiList);
            return "argomenti/detail";
        } else {
            // se l'opional è vuoto sollevo un'eccezione
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }




    // CRUD NOZIONE
    // mostra
    @GetMapping("/{slug}/{id}/{id_nozione}")
    public String showNozione(@PathVariable("slug") String slug,
                              @PathVariable("id") Integer id,
                              @PathVariable("id_nozione") Integer nozione_id,
                              Model model) {
        Optional<UnitaApprendimento> unita = unitaApprendimentoRepository.findById(id);

        if (unita.isPresent()) {
            UnitaApprendimento unitaResult = unita.get();
            Optional<Nozione> result = nozioneRepository.findByIdAndUnitaApprendimento(nozione_id, unitaResult);
            //Optional<Nozione> result=nozioneRepository.findById(nozione_id);
            if (result.isPresent()) {
                Nozione nozione = result.get();
                model.addAttribute("nozione", nozione);
                return "nozioni/detail";
            }else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    // controller che mostra la pagina di creazione di una nozione
    @GetMapping("/{slug}/{id}/create") // url
    public String create( @PathVariable("slug") String slug,
                          @PathVariable("id") Integer id,Model model) {
        // prendo argomento e unita perche mi servono per il form action e link
        Argomento argomento;
        argomento=argomentoRepository.findBySlug(slug).get();
        UnitaApprendimento unitaApprendimento;
        unitaApprendimento= unitaApprendimentoRepository.findById(id).get();
        // aggiungiamo al model un attributo di tipo Book
        model.addAttribute("nozione", new Nozione());
        model.addAttribute("unita", unitaApprendimento);
        model.addAttribute("argomento", argomento);
        return "nozioni/form"; // template
    }

    @PostMapping("/{slug}/{apprendimentoId}/create") // url
    public String doCreate( @PathVariable("slug") String slug,
                          @PathVariable("apprendimentoId") Integer apprendimentoId,Model model,@Valid @ModelAttribute("nozione") Nozione formNozione,
                            BindingResult bindingResult) {
        // prendo argomento e unita perche mi servono per il form action e link
        Argomento argomento;
        argomento=argomentoRepository.findBySlug(slug).get();
        UnitaApprendimento unitaApprendimento;
        unitaApprendimento= unitaApprendimentoRepository.findById(apprendimentoId).get();

        if (bindingResult.hasErrors()) {
            return "nozioni/form"; // template
        }

        formNozione.setUnitaApprendimento(unitaApprendimento);
        // per salvare il book su database chiama in aiuto il bookRepository
        nozioneRepository.save(formNozione);
        // aggiungiamo al model un attributo di tipo Book
        //model.addAttribute("nozione",formNozione);
        //model.addAttribute("unita", unitaApprendimento);
        //model.addAttribute("argomento", argomento);
        // se il book è stato salvato con successo faccio una redirect alla pagina della lista
        return "redirect:/argomenti";

    }


    // update nozione

    @GetMapping("/{slug}/{id}/{id_nozione}/edit")
    String edit(@PathVariable("slug") String slug,
                @PathVariable("id") Integer id,
                @PathVariable("id_nozione") Integer nozione_id, Model model){
        // prendo argomento e unita perche mi servono per il form action e link
        Argomento argomento;
        argomento=argomentoRepository.findBySlug(slug).get();
        UnitaApprendimento unitaApprendimento;
        unitaApprendimento= unitaApprendimentoRepository.findById(id).get();
        Optional<Nozione> nozione=nozioneRepository.findByIdAndUnitaApprendimento(nozione_id,unitaApprendimento);
        // aggiungiamo al model un attributo di tipo Book
        if (nozione.isPresent()){
            model.addAttribute("nozione", nozione.get());
            model.addAttribute("unita", unitaApprendimento);
            model.addAttribute("argomento", argomento);
            return "nozioni/form"; // template
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " not found");
        }

    }


    @PostMapping("/{slug}/{apprendimentoId}/{id_nozione}/edit")
    public String doUpdate(@PathVariable("slug") String slug,
                           @PathVariable("apprendimentoId") Integer apprendimentoId,
                           @PathVariable("id_nozione") Integer id_nozione,
                           Model model,@Valid @ModelAttribute("nozione") Nozione formNozione,
                           BindingResult bindingResult){

        // valido i dati
        if (bindingResult.hasErrors()) {

            return "/nozioni/form"; // nome del template per ricreare la view
        }
        // prendo argomento e unita perche mi servono per il form action e link
        Argomento argomento;
        argomento=argomentoRepository.findBySlug(slug).get();
        UnitaApprendimento unitaApprendimento;
        unitaApprendimento= unitaApprendimentoRepository.findById(apprendimentoId).get();


        formNozione.setId(id_nozione);
        formNozione.setUnitaApprendimento(unitaApprendimento);
        nozioneRepository.save(formNozione);
        return "redirect:/argomenti/{slug}/{apprendimentoId}";

    }


    // CRUD unita apprendimento
    // pagina dell unita
    // mostra unita per argomento
    // MOSTRO LE NOZIONI --- VIEW DELLA UNITA' DI APPRENDIMENTO
    @GetMapping("/{slug}/{id}")
    public String showUnitaNozioni(
            @PathVariable("slug") String slug,
            @PathVariable("id") Integer id,
            Model model
    ) {
        // Trova l'argomento e l'unità di apprendimento basati sugli slug
        Optional<Argomento> argomentoOptional = argomentoRepository.findBySlug(slug);
        Optional<UnitaApprendimento> unitaApprendimentoOptional = unitaApprendimentoRepository.findById(id);


        if (argomentoOptional.isPresent() && unitaApprendimentoOptional.isPresent()) {
            System.out.println("presenti");
            Argomento argomento = argomentoOptional.get();
            UnitaApprendimento unitaApprendimento = unitaApprendimentoOptional.get();



            model.addAttribute("argomento", argomento);
            model.addAttribute("unitaApprendimento", unitaApprendimento);


            return "unitaapprendimento/detail"; // Sostituisci con il nome della tua vista
        } else {
            System.out.println("non presenti");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // crea unita
    @GetMapping("/{slugArgomento}/create")
    public String create(@PathVariable("slugArgomento") String slug,Model model){

        // trova l' argomento perche mi serve lo slug
        Optional<Argomento> argomento = argomentoRepository.findBySlug(slug);
        model.addAttribute("unitaApprendimento", new UnitaApprendimento());
        model.addAttribute("argomento",argomento.get());
        return "unitaapprendimento/form";
    }

    @PostMapping("/{slugArgomento}/create")

    public String doCreate(@PathVariable("slugArgomento") String slug, @Valid @ModelAttribute("unitaApprendimento") UnitaApprendimento unitaForm,BindingResult bindingResult){
        // userForm contiene i dati dello user presi dalla request
        // verifico se ci sono errori
        if (bindingResult.hasErrors()) {
            return "unitaapprendimento/form";
        }
        // lo salvo su database
        Argomento argomento = argomentoRepository.findBySlug(slug).get();
        unitaForm.setArgomento(argomento);
        unitaApprendimentoRepository.save(unitaForm);
        return "redirect:/argomenti/{slugArgomento}";
    }

    // modifica unita
    @GetMapping("/{slug}/{unitaId}/edit")
    public String edit(@PathVariable("slug") String slug ,@PathVariable("unitaId") Integer id, Model model){
        Optional<Argomento> argomento= argomentoRepository.findBySlug(slug);
        if (argomento.isPresent()){
            Optional<UnitaApprendimento> unitaApprendimento= unitaApprendimentoRepository.findById(id);
            if(unitaApprendimento.isPresent()){
                model.addAttribute("unitaApprendimento", unitaApprendimento.get());
                model.addAttribute("argomento",argomento.get());
                return "unitaapprendimento/form";
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "unita apprendimento with id " + id + " not found");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "argomento with id " + id + " not found");

        }



    }

    @PostMapping("/{slug}/{unitaId}/edit")
    // elimina unità
    public String doEdit(@PathVariable("slug") String slug ,@PathVariable("unitaId") Integer id, Model model, @Valid @ModelAttribute("unitaApprendimento") UnitaApprendimento unitaForm,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "unitaapprendimento/form";
        }
        Argomento argomento = argomentoRepository.findBySlug(slug).get();
        unitaForm.setArgomento(argomento);
        unitaForm.setUnita_id(id);
        model.addAttribute("argomento",unitaForm.getArgomento());
        unitaApprendimentoRepository.save(unitaForm);
        return "redirect:/argomenti";
    }

    // cancella unita
    @PostMapping("/{slug}/{id}/delete")
    public String delete(@PathVariable("id") Integer userId,@PathVariable("slug") String slug) {
        // legge l'id dello User dal path
        // cancella lo User da database
        unitaApprendimentoRepository.deleteById(userId);
        // fa una redirect alla lista di User
        return "redirect:/users";
    }


    // CRUD DEGLI ARGOMENTI
    // creo argomento
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("argomento", new Argomento());

        return "argomenti/form";
    }

    @PostMapping("/create")

    public String doCreate(@Valid @ModelAttribute("argomento") Argomento argomentoForm,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return  "argomenti/form";
        }
        // creo e setto lo slug
        // debug
        argomentoForm.setSlug(Utils.getSlug(argomentoForm.getNome()));
        argomentoRepository.save(argomentoForm);
        return "redirect:/argomenti";
    }

    // edit
    @GetMapping("/{slug}/edit")
    String edit(@PathVariable("slug") String slug,Model model){
        Optional<Argomento> result = argomentoRepository.findBySlug(slug);
        if (result.isPresent()){
            model.addAttribute("argomento",result.get());
            return "argomenti/form";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "argomento with slug " + slug + " not found");
        }
    }

    @PostMapping("/{slug}/edit")
    String doEdit(@PathVariable("slug") String slug, @Valid @ModelAttribute("argomento") Argomento argomentoForm ,Model model,BindingResult bindingResult ){
        if (bindingResult.hasErrors()){
            return "argomenti/form";
        }
        Argomento argomento= argomentoRepository.findBySlug(slug).get();

        argomentoForm.setArgomento_id(argomento.getArgomento_id());
        argomentoRepository.save(argomentoForm);
        return "redirect:/argomenti";
    }

    // delete
    // cancella unita
    @PostMapping("/{slug}/delete")
    public String deleteArgomento(@PathVariable("slug") String slug) {

        Argomento argomento= argomentoRepository.findBySlug(slug).get();
        argomentoRepository.deleteById(argomento.getArgomento_id());
        // fa una redirect alla lista di User
        return "redirect:/argomenti";
    }

}
