package br.com.hellmets.motocerol.AndGraph;

import br.com.hellmets.motocerol.R;

/**
 * Created by breno on 12/12/2017.
 */

public class CenaPerdeu extends AGScene
{
    AGSprite perdeu = null;

    //Construtor da Classe
    public CenaPerdeu(AGGameManager manager)
    {
        super(manager);
    }

    @Override
    public void init()
    {
        //Sprite que indica que o jogador perdeu
        perdeu = this.createSprite(R.mipmap.imagem_perdedor, 1,1);
        perdeu.setScreenPercent(30,30);

        perdeu.vrPosition.setXY(AGScreenManager.iScreenWidth/2, AGScreenManager.iScreenHeight/2);

        //Seta a cor de fundo para VERMELHO
        setSceneBackgroundColor(1,0,0);
    }

    @Override
    public void restart()
    {

    }

    @Override
    public void stop()
    {

    }

    @Override
    public void loop()
    {
        //Método de volta para o menu principal do jogo
        //Ao clicar no botão de voltar do celular, volta para a cena de menu
        if(AGInputManager.vrTouchEvents.backButtonClicked())
        {
            //Seleciona a cena de MENU
            this.vrGameManager.setCurrentScene(0);
            return;
        }
    }
}
